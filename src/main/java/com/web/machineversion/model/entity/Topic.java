package com.web.machineversion.model.entity;

import java.util.Date;

public class Topic {
    private Integer topicId;

    private Integer userId;

    private String title;

    private String content;

    private String theme;

    private Date createTime;

    private Date updateTime;

    private Integer topicCommentNum;

    private Integer topicLikeNum;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTopicCommentNum() {
        return topicCommentNum;
    }

    public void setTopicCommentNum(Integer topicCommentNum) {
        this.topicCommentNum = topicCommentNum;
    }

    public Integer getTopicLikeNum() {
        return topicLikeNum;
    }

    public void setTopicLikeNum(Integer topicLikeNum) {
        this.topicLikeNum = topicLikeNum;
    }
}