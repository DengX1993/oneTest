package com.css.demo.service;

import com.css.demo.bean.UserBean;

public interface UserService {

    UserBean loginIn(String name,String password);

}