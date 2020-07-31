package com.css.demo.service;

import com.css.demo.bean.ContentDesignBean;

import java.util.List;

public interface ContentDesignService {
    ContentDesignBean selectObjByUUid (String uuid);
    List<ContentDesignBean> selectAllByScene(String scene);
}
