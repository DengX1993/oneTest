package com.css.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface AccountMapper {

    @Insert("insert into account(UUID,USER_NUMBER,LAST_LOGIN_TIME) values(#{uuid},#{userNumber},#{lastLoginTime})")
    int insert(@Param("uuid") String uuid,@Param("userNumber") String userNumber,@Param("lastLoginTime") Date lastLoginTime);

}
