package com.css.demo.mapper;

import com.css.demo.bean.ContentDesignBean;

import java.util.List;
import java.util.Map;


public interface ContentDesignMapper {

    ContentDesignBean selectObjByUUid (String uuid);

    List<Map<String,ContentDesignBean>> selectAllByScene(String scene);

    List<Map<String,ContentDesignBean>> selectAllInvitationByScene(String scene);
}
