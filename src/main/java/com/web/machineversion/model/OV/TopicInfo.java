package com.web.machineversion.model.OV;

<<<<<<< HEAD
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
=======
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

>>>>>>> master

@Data
public class TopicInfo {

<<<<<<< HEAD
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


=======
    @JsonProperty("author")
    private AuthorInfo topicAuthor;

    @JsonProperty("time")
    private String createTime;

    @JsonProperty("theme")
    private String topicTheme;

    @JsonProperty("title")
    private String topicTitle;

    @JsonProperty("topicId")
    private String topicId;

    @JsonProperty("like")
    private Integer topicLike;

    @JsonProperty("comment")
    private Integer topicCommentNum;

    @JsonProperty("liked")
    private Boolean topicLiked;

    @JsonProperty("commented")
    private Boolean topicCommented;
>>>>>>> master
}
