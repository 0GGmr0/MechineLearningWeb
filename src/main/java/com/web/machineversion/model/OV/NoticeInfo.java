package com.web.machineversion.model.OV;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NoticeInfo {
    @JsonProperty(value="type")
    private String noticeType;
    @JsonProperty(value="title")
    private String noticeTitle;
    @JsonProperty(value="content")
    private String noticeContent;
    @JsonProperty(value="author")
    private String noticeAuthor;
    @JsonProperty(value="time")
    private String noticeTime;
    @JsonProperty(value="publishTime")
    private String publishTime;
}
