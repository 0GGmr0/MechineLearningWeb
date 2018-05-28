package com.web.machineversion.model.OV;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class CommentInfo {

    //回复的Id
    @JSONField(name="replyId")
    private Integer commentId;

    //回复者头像
    @JSONField(name="avatar")
    private String authorAvatar;

    //回复者信息
    @JSONField(name="author")
    private TopicAuthor commentAuthor;

    //回复内容
    @JSONField(name="content")
    private String content;

    //创建时间
    @JSONField(name="createTime")
    private Date time;

    //点赞数
    @JSONField(name="replyLikeNum")
    private TopicAuthor like;

    //是否点赞
    @JSONField(name="liked")
    private Boolean liked;

}
