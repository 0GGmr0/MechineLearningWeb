package com.web.machineversion.model.jsonrequestbody;

import lombok.Data;

@Data
public class TopicQueryJson {
    //话题标题
    private String title;

    //话题主题
    private String type;

    //话题内容
    private String content;

    private Integer topicId;
}
