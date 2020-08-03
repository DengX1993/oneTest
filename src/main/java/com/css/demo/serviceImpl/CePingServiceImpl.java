package com.css.demo.serviceImpl;

import com.css.demo.bean.CePingBean;
import com.css.demo.mapper.CePingMapper;
import com.css.demo.service.CePingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CePingServiceImpl implements CePingService {
    //将DAO注入Service层
    @Autowired
    private CePingMapper cePingMapper;

    @Override
    public int insert(String uuid, String userNumber, String y1, String o1, String y2, String o2, String y3, String o3, String y4, String o4, String y5, String o5, String y6, String o6) {
        return cePingMapper.insert(uuid,userNumber,y1,o1,y2,o2,y3,o3,y4,o4,y5,o5,y6,o6);
    }

    @Override
    public int updCepingCheckTime(CePingBean cePingBean) {
        return cePingMapper.updCepingCheckTime(cePingBean);
    }

    @Override
    public CePingBean selectBeanByUuid(String uuid) {
        return cePingMapper.selectBeanByUuid(uuid);
    }
}
