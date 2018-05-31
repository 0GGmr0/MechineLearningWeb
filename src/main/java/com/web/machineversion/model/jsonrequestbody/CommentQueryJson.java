package com.web.machineversion.model.jsonrequestbody;

import lombok.Data;

@Data
public class CommentQueryJson {
    //需要评论的话题
    private Integer topicId;

    //评论的内容
    private String content;
}
