package com.web.machineversion.model.entity;

import java.util.Date;

public class Reply {
    private Integer replyId;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    private Integer topicId;

    private Integer replyLikeNum;

    private String content;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getReplyLikeNum() {
        return replyLikeNum;
    }

    public void setReplyLikeNum(Integer replyLikeNum) {
        this.replyLikeNum = replyLikeNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}