package com.css.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


public interface AccountMapper {

    @Insert("insert into account(UUID,USER_NUMBER,LAST_LOGIN_TIME) values(#{name})")
    String insert(@Param("uuid") String uuid,@Param("userNumber") String userNumber,@Param("lastLoginTime") Date lastLoginTime);

}
