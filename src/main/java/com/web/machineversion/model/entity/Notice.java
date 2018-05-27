package com.web.machineversion.model.entity;

import java.util.Date;

public class Notice {
    private Integer id;

    private String title;

    private String content;

    private Date createTime;

    private String author;

    private String type;

    private Date eventTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date time) {
        this.eventTime = time;
    }

}