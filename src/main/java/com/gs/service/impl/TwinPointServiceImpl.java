package com.gs.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.DTO.CalculateScriptTestDTO;
import com.gs.config.Constant;
import com.gs.dao.entity.OPCItemValueRecordEntity;
import com.gs.dao.entity.TwinPointEntity;
import com.gs.dao.mapper.TwinPointMapper;
import com.gs.service.CalculateScriptService;
import com.gs.service.OPCItemValueRecordService;
import com.gs.service.TwinPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


/**
 * @author ：YoungSun
 * @date ：Created in 2021/1/11 14:20
 * @modified By：
 */
@Service
public class TwinPointServiceImpl extends ServiceImpl<TwinPointMapper, TwinPointEntity> implements TwinPointService {

    @Autowired
    CalculateScriptService calculateScriptService;

    @Autowired
    TwinPointMapper twinPointMapper;

    @Autowired
    OPCItemValueRecordService opcItemValueRecordService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public List<TwinPointEntity> twinPointForUpdateValue() {
        List<TwinPointEntity> twinPointEntities = twinPointMapper.twinPointForUpdateValue();
        return twinPointEntities;
    }

    @Override
    @Async
    public void pointValueUpdate(Long pointId) {
        TwinPointEntity pointEntity = twinPointMapper.selectOne(new QueryWrapper<TwinPointEntity>().eq("id", pointId));
        if (pointEntity == null) {
            throw new RuntimeException("孪生点位不存在");
        }
        if (pointEntity.getDataType() == 2) {
            CalculateScriptTestDTO calculateScriptTestDTO = new CalculateScriptTestDTO();
            calculateScriptTestDTO.setFactoryId(pointEntity.getFactoryId());
            calculateScriptTestDTO.setCalculateScript(pointEntity.getCalculateScript());
            Double aDouble = calculateScriptService.calculateScriptRun(calculateScriptTestDTO);
            BigDecimal b = new BigDecimal(aDouble);
            aDouble = b.setScale(pointEntity.getDecimalPalces(), BigDecimal.ROUND_HALF_UP).doubleValue();
            pointEntity.setPointValue(String.valueOf(aDouble));
            //更新下次更新时间
            pointEntity.setNextUpdateTime(LocalDateTime.now().plus(pointEntity.getCalculateFrequency(), ChronoUnit.SECONDS));
            //更新redis缓存
            twinPointMapper.updateById(pointEntity);
            //孪生点位缓存
            this.twinPointCache(pointEntity);
        } else if (pointEntity.getDataType() == 1) {
            IPage<OPCItemValueRecordEntity> page = opcItemValueRecordService.page(new Page<>(1, 1), new QueryWrapper<OPCItemValueRecordEntity>().eq("factory_id", pointEntity.getFactoryId()).eq("item_id", pointEntity.getItemId()).orderBy(true, false, "item_timestamp"));
            List<OPCItemValueRecordEntity> records = page.getRecords();
            if (CollectionUtils.isNotEmpty(records)) {
                pointEntity.setPointValue(records.get(0).getItemValue());
                //更新下次更新时间
                pointEntity.setNextUpdateTime(LocalDateTime.now().plus(pointEntity.getCalculateFrequency(), ChronoUnit.SECONDS));
                twinPointMapper.updateById(pointEntity);
                this.twinPointCache(pointEntity);
            }
        }
    }

    @Override
    public void twinPointCache(TwinPointEntity twinPointEntity) {
        stringRedisTemplate.opsForValue().set(Constant.REDIS_TWIN_POINT_CACHE_PREFIX + twinPointEntity.getFactoryId() + ":" + twinPointEntity.getPointId(), JSON.toJSONString(twinPointEntity));
    }


}
