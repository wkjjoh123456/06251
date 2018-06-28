package com.zjt.mapper;

import com.zjt.entity.Dfoodpar;
import com.zjt.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DfoodparMapper extends MyMapper<Dfoodpar> {

    List<Dfoodpar> selecTotal();
}