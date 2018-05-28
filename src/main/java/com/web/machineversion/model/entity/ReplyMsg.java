package com.web.machineversion.model.entity;

public class ReplyMsg {
    private Integer replyMsgId;

    private Integer replyId;

    private Integer likeUserId;

    public Integer getReplyMsgId() {
        return replyMsgId;
    }

    public void setReplyMsgId(Integer replyMsgId) {
        this.replyMsgId = replyMsgId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getLikeUserId() {
        return likeUserId;
    }

    public void setLikeUserId(Integer likeUserId) {
        this.likeUserId = likeUserId;
    }
}