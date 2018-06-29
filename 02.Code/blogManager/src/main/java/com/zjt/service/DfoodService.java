package com.zjt.service;


import com.zjt.entity.Dfoodlist;
import com.zjt.entity.Dfoodpar;
import com.zjt.model.Dfoodtotallist;

import java.util.List;
import java.util.Map;

public interface DfoodService extends IService<Dfoodlist> {
    //查找食物
    public List<Dfoodtotallist> findfood(Map<String, Object> map);

    //分页返回总数
    public long getTotalfood(Map<String, Object> map);

    //查找父级id
    public List<Dfoodpar> selectParentId();

    //添加食物
    public int addFood(Dfoodlist dfoodlist);

    //删除食物
    public int deleteFood(String id);

    //更新食物
    public int updateFood(Dfoodlist dfoodlist);

    //查找食物类别
    public List<Dfoodpar> findFoodKind(Map<String, Object> map);

    //食物类别分页总数
    public long getTotalFoodKind(Map<String, Object> map);

    // 添加食物类别
    public int addFoodKind(Dfoodpar dfoodpar);

    // 修改食物类别
    public int updateFoodKind(Dfoodpar dfoodpar);

    // 删除食物类别
    public int deleteFoodKind(String id);
}
