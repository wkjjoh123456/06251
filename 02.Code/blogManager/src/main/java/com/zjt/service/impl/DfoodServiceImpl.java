package com.zjt.service.impl;

import com.zjt.entity.Dfoodlist;
import com.zjt.entity.Dfoodpar;
import com.zjt.mapper.DfoodparMapper;
import com.zjt.model.Dfoodtotallist;
import com.zjt.mapper.DfoodlistMapper;
import com.zjt.service.DfoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("dfoodService")
public class DfoodServiceImpl extends BaseService<Dfoodlist> implements DfoodService {
@Autowired
private DfoodlistMapper dfoodlistMapper;

@Autowired
private DfoodparMapper dfoodparMapper;
    @Override
    public List<Dfoodtotallist> findfood(Map<String, Object> map) {
        return dfoodlistMapper.findfood(map);
    }

    @Override
    public long getTotalfood(Map<String, Object> map) {
        return dfoodlistMapper.getTotalfood(map);
    }

    @Override
    public List<Dfoodpar> selectParentId() {
        return dfoodparMapper.selecTotal();
    }

    @Override
    public int addFood(Dfoodlist dfoodlist) {
        return dfoodlistMapper.addFood(dfoodlist);
    }

    @Override
    public int deleteFood(String id) {
        return dfoodlistMapper.deleteFood(id);
    }

    @Override
    public int updateFood(Dfoodlist dfoodlist) {
        return dfoodlistMapper.updateFood(dfoodlist);
    }

    @Override
    public List<Dfoodpar> findFoodKind(Map<String, Object> map) {
        return dfoodparMapper.selectKind(map);
    }

    @Override
    public long getTotalFoodKind(Map<String, Object> map) {
        return dfoodparMapper.getTotalfoodKind(map);
    }

    @Override
    public int addFoodKind(Dfoodpar dfoodpar) {
        return dfoodparMapper.addFoodKind(dfoodpar);
    }

    @Override
    public int updateFoodKind(Dfoodpar dfoodpar) {
        return dfoodparMapper.updateFoodKind(dfoodpar);
    }

    @Override
    public int deleteFoodKind(String id) {
        return dfoodparMapper.deleteFoodKind(id);
    }
}
