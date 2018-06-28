package com.zjt.model;

import com.zjt.entity.Dfoodpar;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


public class Dfoodlist {
    public Dfoodpar getDfoodpar() {
        return dfoodpar;
    }

    public void setDfoodpar(Dfoodpar dfoodpar) {
        this.dfoodpar = dfoodpar;
    }
    private Dfoodpar dfoodpar;

    private Integer id;


    private Integer fIsquick;


    private Integer fParentid;


    private Long fPro;

    private Long fCar;

    private Long fFat;


    private Long fEne;


    private String fName;

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getfIsquick() {
        return fIsquick;
    }


    public void setfIsquick(Integer fIsquick) {
        this.fIsquick = fIsquick;
    }

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