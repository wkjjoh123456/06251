package com.zjt.mapper;

import com.zjt.entity.Dfoodlist;

import com.zjt.model.Dfoodtotallist;
import com.zjt.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface DfoodlistMapper extends MyMapper<Dfoodlist> {

    List<Dfoodtotallist> findfood(Map<String, Object> map);
    Long getTotalfood(Map<String, Object> map);
    int addFood(Dfoodlist dfoodlist);
    int deleteFood(String id);
    int updateFood(Dfoodlist dfoodlist);
}