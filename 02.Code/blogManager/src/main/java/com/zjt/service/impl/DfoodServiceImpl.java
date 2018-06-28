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
}
