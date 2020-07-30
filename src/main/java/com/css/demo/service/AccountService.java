package com.css.demo.service;

import java.util.Date;

public interface AccountService {
    int insert (String uuid, String userNumber, Date lastLoginTime);
}
