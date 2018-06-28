package com.zjt.entity;

import javax.persistence.*;

@Table(name = "djxt_food_list")
public class Dfoodlist {
    /**
     * 食物id主键
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 1:快速记录,2:不是快速记录
     */
    @Column(name = "f_Isquick")
    private Integer fIsquick;

    /**
     * 父级id（自定义为0）
     */
    @Column(name = "f_ParentId")
    private Integer fParentid;

    /**
     * 食用100g蛋白质
     */
    @Column(name = "f_Pro")
    private Long fPro;

    /**
     * 食用100g碳水化合物
     */
    @Column(name = "f_Car")
    private Long fCar;

    /**
     * 食用100g脂肪
     */
    @Column(name = "f_Fat")
    private Long fFat;

    /**
     * 食用100g能量
     */
    @Column(name = "f_Ene")
    private Long fEne;

    /**
     * 食物名字
     */
    @Column(name = "f_Name")
    private String fName;

    /**
     * 获取食物id主键
     *
     * @return f_Id - 食物id主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置食物id主键
     *
     * @param id
     * 食物id主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取1:快速记录,2:不是快速记录
     *
     * @return f_Isquick - 1:快速记录,2:不是快速记录
     */
    public Integer getfIsquick() {
        return fIsquick;
    }

    /**
     * 设置1:快速记录,2:不是快速记录
     *
     * @param fIsquick 1:快速记录,2:不是快速记录
     */
    public void setfIsquick(Integer fIsquick) {
        this.fIsquick = fIsquick;
    }

    /**
     * 获取父级id（自定义为0）
     *
     * @return f_ParentId - 父级id（自定义为0）
     */
    public Integer getfParentid() {
        return fParentid;
    }

    /**
     * 设置父级id（自定义为0）
     *
     * @param fParentid 父级id（自定义为0）
     */
    public void setfParentid(Integer fParentid) {
        this.fParentid = fParentid;
    }

    /**
     * 获取食用100g蛋白质
     *
     * @return f_Pro - 食用100g蛋白质
     */
    public Long getfPro() {
        return fPro;
    }

    /**
     * 设置食用100g蛋白质
     *
     * @param fPro 食用100g蛋白质
     */
    public void setfPro(Long fPro) {
        this.fPro = fPro;
    }

    /**
     * 获取食用100g碳水化合物
     *
     * @return f_Car - 食用100g碳水化合物
     */
    public Long getfCar() {
        return fCar;
    }

    /**
     * 设置食用100g碳水化合物
     *
     * @param fCar 食用100g碳水化合物
     */
    public void setfCar(Long fCar) {
        this.fCar = fCar;
    }

    /**
     * 获取食用100g脂肪
     *
     * @return f_Fat - 食用100g脂肪
     */
    public Long getfFat() {
        return fFat;
    }

    /**
     * 设置食用100g脂肪
     *
     * @param fFat 食用100g脂肪
     */
    public void setfFat(Long fFat) {
        this.fFat = fFat;
    }

    /**
     * 获取食用100g能量
     *
     * @return f_Ene - 食用100g能量
     */
    public Long getfEne() {
        return fEne;
    }

    /**
     * 设置食用100g能量
     *
     * @param fEne 食用100g能量
     */
    public void setfEne(Long fEne) {
        this.fEne = fEne;
    }

    /**
     * 获取食物名字
     *
     * @return f_Name - 食物名字
     */
    public String getfName() {
        return fName;
    }

    /**
     * 设置食物名字
     *
     * @param fName 食物名字
     */
    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }
}