package com.zjt.entity;

import javax.persistence.*;

@Table(name = "djxt_food_parent")
public class Dfoodpar {
    @Id
    @Column(name = "fp_id")
    private Integer fpId;

    /**
     * 类别0自定义
     */
    @Column(name = "fp_name")
    private String fpName;

    /**
     * @return fp_id
     */
    public Integer getFpId() {
        return fpId;
    }

    /**
     * @param fpId
     */
    public void setFpId(Integer fpId) {
        this.fpId = fpId;
    }

    /**
     * 获取类别0自定义
     *
     * @return fp_name - 类别0自定义
     */
    public String getFpName() {
        return fpName;
    }

    /**
     * 设置类别0自定义
     *
     * @param fpName 类别0自定义
     */
    public void setFpName(String fpName) {
        this.fpName = fpName == null ? null : fpName.trim();
    }
}