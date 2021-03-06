package com.gs.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.dao.entity.TwinPointAvgEntity;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

public interface TwinPointAvgMapper extends BaseMapper<TwinPointAvgEntity> {
    Float twinPointAvg(@Param("pointId") Long pointId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
