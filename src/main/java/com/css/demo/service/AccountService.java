package com.css.demo.service;

import java.util.Date;

public interface AccountService {
    String insert(String uuid, String userNumber, Date lastLoginTime);
}
