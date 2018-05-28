package com.web.machineversion.model.jsonrequestbody;

import lombok.Data;

/**
 * {
 *   "topicId":100003
 *   "commentId": 3
 * }
 */

@Data
public class CommentLIkeQueryJason {
    //话题Id
    private Integer topicId;

    //评论Id
    private Integer commentId;
}
