package com.css.demo.common;

import java.util.UUID;

public class UUidUtil {
    public static String getUUid(){
        String getid=UUID.randomUUID().toString().replace("-", "");
        return getid;
    }
}
