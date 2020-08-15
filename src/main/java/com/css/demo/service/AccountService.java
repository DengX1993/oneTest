package com.css.demo.service;

import com.css.demo.bean.AccountBean;

import java.util.Date;

public interface AccountService {
    int insert (String uuid, String userNumber, Date lastLoginTime);
    AccountBean selectBeanByUuid(String uuid);
}
