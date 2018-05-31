package com.web.machineversion.model.entity;

import java.util.Date;

public class Notice {
    private Integer noticeId;

    private Integer userId;

    private Date createTime;

    private String type;

    private Date eventTime;
<<<<<<< HEAD

    private String content;
=======
>>>>>>> master

    private String title;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
<<<<<<< HEAD
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

=======
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

>>>>>>> master
    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

<<<<<<< HEAD
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
=======
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
>>>>>>> master
    }
}