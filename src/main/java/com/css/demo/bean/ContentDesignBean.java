package com.css.demo.bean;

//内容设计标
public class ContentDesignBean {

    //uuid
    public String uuid;
    //场景 1,2,3,4
    public String scene;
    //标题
    public String title;
    //内容类型。1主要内容。2评论。3帖子
    public String contentType;
    //内容
    public String contents;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
