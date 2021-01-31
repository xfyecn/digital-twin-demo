package com.gs.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

/**
 * @author ：YoungSun
 * @date ：Created in 2021/1/31 16:48
 * @modified By：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class HFSFCompoundFertilizerQualityEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 日期
     */
    @TableField("date")
    private LocalDateTime date;
    /**
     * 品名
     */
    @TableField("production_name")
    private String productionName;

    /**
     * 班次 1:甲 2:乙 3:丙 4:丁
     */
    @TableField("shift")
    private Integer shift;

    /**
     * 0:白班 1:夜班
     */
    @TableField("shift_type")
    private Integer shiftType;

    /**
     * 养分 N
     */
    @TableField("n")
    private Float n = 0f;

    /**
     * 养分 P
     */
    @TableField("p")
    private Float p = 0f;

    /**
     * 养分 K
     */
    @TableField("k")
    private Float k = 0f;

    /**
     * 总养分
     */
    @TableField("total_nutrition")
    private Float totalNutrition = 0f;

    /**
     * 水分
     */
    @TableField("water")
    private Float water = 0f;

    /**
     * 甲一级品
     */
    @TableField("a_first_class")
    private Float AFirstClass = 0f;
    /**
     * 甲自用包
     */
    @TableField("a_own_use_package")
    private Float AOwnUsePackage = 0f;
    /**
     * 甲超养分
     */
    @TableField("a_over_nutrition")
    private Float AOverNutrition = 0f;
    /**
     * 甲超水分
     */
    @TableField("a_over_water")
    private Float AOverWater = 0f;
    /**
     * 甲不合格
     */
    @TableField("a_unqualified")
    private Float AUnqualified = 0f;


    /**
     * 乙一级品
     */
    @TableField("b_first_class")
    private Float BFirstClass = 0f;
    /**
     * 乙自用包
     */
    @TableField("b_own_use_package")
    private Float BOwnUsePackage = 0f;
    /**
     * 乙超养分
     */
    @TableField("b_over_nutrition")
    private Float BOverNutrition = 0f;
    /**
     * 乙超水分
     */
    @TableField("b_over_water")
    private Float BOverWater = 0f;
    /**
     * 乙不合格
     */
    @TableField("b_unqualified")
    private Float BUnqualified = 0f;

    /**
     * 丙一级品
     */
    @TableField("c_first_class")
    private Float CFirstClass = 0f;
    /**
     * 丙自用包
     */
    @TableField("c_own_use_package")
    private Float COwnUsePackage = 0f;
    /**
     * 丙超养分
     */
    @TableField("c_over_nutrition")
    private Float COverNutrition = 0f;
    /**
     * 丙超水分
     */
    @TableField("c_over_water")
    private Float COverWater = 0f;
    /**
     * 丙不合格
     */
    @TableField("c_unqualified")
    private Float CUnqualified = 0f;


    /**
     * 丁一级品
     */
    @TableField("d_first_class")
    private Float DFirstClass = 0f;
    /**
     * 丁自用包
     */
    @TableField("d_own_use_package")
    private Float DOwnUsePackage = 0f;
    /**
     * 丁超养分
     */
    @TableField("d_over_nutrition")
    private Float DOverNutrition = 0f;
    /**
     * 丁超水分
     */
    @TableField("d_over_water")
    private Float DOverWater = 0f;
    /**
     * 丁不合格
     */
    @TableField("d_unqualified")
    private Float DUnqualified = 0f;

    /**
     * 班产
     */
    @TableField("shift_yeild")
    private Float shiftYeild = 0f;

    /**
     * 高浓度
     */
    @TableField("high_concentration")
    private Float highConcentration = 0f;

    /**
     * 中浓度
     */
    @TableField("middle_concentration")
    private Float middleConcentration = 0f;

    /**
     * 低浓度
     */
    @TableField("low_concentration")
    private Float lowConcentration = 0f;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
