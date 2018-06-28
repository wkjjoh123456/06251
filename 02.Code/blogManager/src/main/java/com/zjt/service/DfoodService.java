package com.zjt.service;


import com.zjt.entity.Dfoodlist;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public interface DfoodService extends IService<Dfoodlist>{

    public List<Dfoodlist> findfood(Map<String, Object> map);

    public long getTotalfood(Map<String, Object> map);
}
