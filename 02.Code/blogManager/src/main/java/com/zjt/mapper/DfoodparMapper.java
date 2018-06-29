package com.zjt.mapper;

import com.zjt.entity.Dfoodpar;
import com.zjt.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DfoodparMapper extends MyMapper<Dfoodpar> {
//查询所有的食物类别（food表显示下拉哐）
    List<Dfoodpar> selecTotal();
    //查询所有的食物
    List<Dfoodpar> selectKind(Map<String, Object> map);
    //分页返回总数
     long getTotalfoodKind(Map<String, Object> map);
     // 添加食物类别
   int addFoodKind(Dfoodpar dfoodpar);
   int deleteFoodKind(String id);
   int updateFoodKind(Dfoodpar dfoodpar);
}