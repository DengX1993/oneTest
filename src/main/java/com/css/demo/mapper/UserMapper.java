package com.css.demo.mapper;

import com.css.demo.bean.UserBean;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    UserBean getInfo(String name,String password);

}