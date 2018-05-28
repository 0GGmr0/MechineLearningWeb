package com.web.machineversion.model.OV;

import com.alibaba.fastjson.annotation.JSONField;
import com.web.machineversion.model.entity.TopicMsg;
import lombok.Data;

import java.util.Date;

@Data
public class TopicInfo {

    @JSONField(name="author")
    private TopicAuthor topicAuthor;

    @JSONField(name="time")
    private Date createTime;

    @JSONField(name="theme")
    private String topicTheme;

    @JSONField(name="title")
    private String topicTitle;

    @JSONField(name="topicId")
    private String topicId;

    @JSONField(name="like")
    private String topicLike;

    @JSONField(name="comment")
    private Integer topicCommentNum;

    @JSONField(name="liked")
    private Boolean topicLiked;

    @JSONField(name="commented")
    private Boolean topicCommented;

}
