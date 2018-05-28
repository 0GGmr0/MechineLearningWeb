package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class CommentInfo {
    //回复的Id
    @JsonProperty("commentId")
    private Integer commentId;

    //回复者头像
    @JsonProperty("avatar")
    private String authorAvatar;

    //回复者信息
    @JsonProperty("user")
    private AuthorInfo commentAuthor;

    //回复内容
    @JsonProperty("content")
    private String content;

    //创建时间
    @JsonProperty("time")
    private String time;

    //点赞数
    @JsonProperty("like")
    private Integer like;

    //是否点赞
    @JsonProperty("liked")
    private Boolean liked;
}
