package com.css.demo.serviceImpl;

import com.css.demo.bean.AccountBean;
import com.css.demo.mapper.AccountMapper;
import com.css.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {
    //将DAO注入Service层
    @Autowired
    private AccountMapper accountMapper;


    @Override
    public int insert(String uuid, String userNumber, Date lastLoginTime) {
        return accountMapper.insert(uuid,userNumber,lastLoginTime);
    }

    @Override
    public AccountBean selectBeanByUuid(String uuid) {
        return accountMapper.selectBeanByUuid(uuid);
    }
}
