package com.css.demo.bean;

import java.sql.Date;

public class LogsBean {
    private String uuid;
    //用户uuid
    private String userUuid;
    //浏览内容id
    private String checkContentId;
    //浏览开始时间
    private Date checkEndTime;
    //浏览结束时间
    private Date checkBeginTime;
    //评论状态  0 未评论 1 评论了
    private int commentFlag;
    //评论内容
    private String comment;
}
