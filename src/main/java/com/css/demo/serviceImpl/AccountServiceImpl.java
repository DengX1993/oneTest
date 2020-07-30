package com.css.demo.serviceImpl;

import com.css.demo.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class AccountServiceImpl {
    //将DAO注入Service层
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    String insert (String uuid, String userNumber, Date lastLoginTime){
        return accountMapper.insert(uuid,userNumber,lastLoginTime);
    }

}
