package com.css.demo.mapper;

import com.css.demo.bean.UserBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    UserBean getInfo(String name,String password);


}