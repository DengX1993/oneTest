package com.css.demo.serviceImpl;

import com.css.demo.bean.UserBean;
import com.css.demo.mapper.UserMapper;
import com.css.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    //将DAO注入Service层
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBean loginIn(String name, String password) {
        return userMapper.getInfo(name,password);
    }



}