package com.web.machineversion.model.OV;

import com.alibaba.fastjson.annotation.JSONField;

public class TopicAuthor {
    @JSONField(name = "name")
     private String authorName;
    @JSONField(name="uid")
    private Integer AuthorUid;
}
