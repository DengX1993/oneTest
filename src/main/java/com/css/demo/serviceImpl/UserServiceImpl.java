package com.css.demo.serviceImpl;

import com.css.demo.bean.UserBean;
import com.css.demo.mapper.UserMapper;
import com.css.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    //将DAO注入Service层
    @Resource
    private UserMapper userMapper;

    @Override
    public UserBean loginIn(String name, String password) {
        return userMapper.getInfo(name,password);
    }
}