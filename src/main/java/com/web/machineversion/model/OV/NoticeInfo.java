package com.web.machineversion.model.OV;

import com.alibaba.fastjson.annotation.JSONField;
<<<<<<< HEAD
import lombok.Data;

import java.util.Date;

@Data
public class NoticeInfo {
    @JSONField(name="type")
    private String NoticeType;
    @JSONField(name="title")
    private String NoticeTitle;
    @JSONField(name="content")
    private String NoticeContent;
    @JSONField(name="author")
    private String NoticeAuthor;
    @JSONField(name="time")
    private String NoticeTime;
    @JSONField(name="publishTime")
=======
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
>>>>>>> master
    private String publishTime;
}
