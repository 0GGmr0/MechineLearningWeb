package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TopicInfo {

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
}
