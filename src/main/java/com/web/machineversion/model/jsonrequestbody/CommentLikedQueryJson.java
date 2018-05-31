package com.web.machineversion.model.jsonrequestbody;

import lombok.Data;

@Data
public class CommentLikedQueryJson {
    //话题Id
    private Integer topicId;

    //评论Id
    private Integer commentId;
}
