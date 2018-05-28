package com.web.machineversion.model.OV;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class TopicDetailInfo {
    //标题
    @JSONField(name="title")
    private String topicTitle;

    //主题
    @JSONField(name="theme")
    private String topicType;

    //作者信息
    @JSONField(name="author")
    private TopicAuthor topicAuthor;

    //作者头像
    @JSONField(name="avatar")
    private String authorAvatar;

    //创建时间
    @JSONField(name="createTime")
    private Date datetime;

    //内容
    @JSONField(name="content")
    private  String content;
}
