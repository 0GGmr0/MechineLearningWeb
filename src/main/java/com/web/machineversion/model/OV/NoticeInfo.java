package com.web.machineversion.model.OV;

import com.alibaba.fastjson.annotation.JSONField;
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
    private String publishTime;
}
