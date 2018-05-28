package com.web.machineversion.model.entity;

public class TopicMsg {
    private Integer topicMsgId;

    private Integer topicId;

    private Integer replyUserId;

    private Integer liked;

    private Integer commented;

    public Integer getTopicMsgId() {
        return topicMsgId;
    }

    public void setTopicMsgId(Integer topicMsgId) {
        this.topicMsgId = topicMsgId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

    public Integer getCommented() {
        return commented;
    }

    public void setCommented(Integer commented) {
        this.commented = commented;
    }

    public void getCommented(Object commented) {
    }

    public void getLiked(Object liked) {
    }

    public void setCommented(boolean b) {
    }
}