package com.css.demo.bean;


import java.util.Date;

public class LogsBean {
    private String uuid;
    //用户uuid
    private String userUuid;
    //浏览内容id
    private String checkContentId;
    //评论状态  0 未评论 1 评论了
    private int commentFlag;
    //评论内容
    private String comment;
    //创建时间
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getCheckContentId() {
        return checkContentId;
    }

    public void setCheckContentId(String checkContentId) {
        this.checkContentId = checkContentId;
    }

    public int getCommentFlag() {
        return commentFlag;
    }

    public void setCommentFlag(int commentFlag) {
        this.commentFlag = commentFlag;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
