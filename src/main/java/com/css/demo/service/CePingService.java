package com.css.demo.service;

import com.css.demo.bean.AccountBean;
import com.css.demo.bean.CePingBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CePingService {

    int insert (String uuid, String userNumber,String y1,String o1,String y2,String o2,String y3,String o3
                ,String y4,String o4,String y5,String o5,String y6,String o6);
    int updCepingCheckTime(CePingBean cePingBean);
    CePingBean selectBeanByUuid(String uuid);

}
