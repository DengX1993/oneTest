package com.css.demo.serviceImpl;

import com.css.demo.bean.RecordBean;
import com.css.demo.mapper.CePingMapper;
import com.css.demo.mapper.RecordMapper;
import com.css.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public int insert(RecordBean record) {
        return recordMapper.insert(record);
    }

    @Override
    public int insertSelective(RecordBean record) {
        return recordMapper.insertSelective(record);
    }

}
