package com.web.machineversion.model.entity;

public class ReplyMsg {
    private Integer replyMsgId;

    private Integer replyId;

    private Integer likeUserId;

    private Integer likeed;

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

    public Integer getLikeed() {
        return likeed;
    }

    public void setLikeed(Integer likeed) {
        this.likeed = likeed;
    }
}