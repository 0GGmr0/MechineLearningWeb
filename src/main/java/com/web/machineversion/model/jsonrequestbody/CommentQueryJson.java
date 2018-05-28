package com.web.machineversion.model.jsonrequestbody;

import lombok.Data;

/**
 * {
 *   "topicId":100003
 *   "content":"这是内容，这个内容应该不太长，用什么存看你喜欢吧"
 * }
 */

@Data
public class CommentQueryJson {
    //需要评论的话题
    private Integer topicId;

    //评论的内容
    private String content;
}
