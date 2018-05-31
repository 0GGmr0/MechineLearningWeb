package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TopicDetailInfo {
    //标题
    @JsonProperty("title")
    private String topicTitle;

    //主题
    @JsonProperty("type")
    private String topicType;

    //作者信息
    @JsonProperty("author")
    private AuthorInfo topicAuthor;

    //作者头像
    @JsonProperty("authorAvatar")
    private String authorAvatar;

    //创建时间
    @JsonProperty("datetime")
    private String dateTime;

    //内容
    @JsonProperty("content")
    private  String content;
}
