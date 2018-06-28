package com.zjt.service;


import com.zjt.entity.Dfoodlist;
import com.zjt.entity.Dfoodpar;
import com.zjt.model.Dfoodtotallist;

import java.util.List;
import java.util.Map;

public interface DfoodService extends IService<Dfoodlist>{

    public List<Dfoodtotallist> findfood(Map<String, Object> map);


    public long getTotalfood(Map<String, Object> map);

    public List<Dfoodpar> selectParentId();
}
