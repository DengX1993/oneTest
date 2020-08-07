package com.css.demo.service;

import com.css.demo.bean.RecordBean;

public interface RecordService {

    int insert(RecordBean record);
    int insertSelective(RecordBean record);
}
