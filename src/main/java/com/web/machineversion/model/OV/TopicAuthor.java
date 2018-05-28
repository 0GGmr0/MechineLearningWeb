package com.web.machineversion.model.OV;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class TopicAuthor {
    @JSONField(name = "name")
     private String authorName;
    @JSONField(name="uid")
    private Integer AuthorUid;

    public void getAuthorName(String userName) {
    }

    public void getAuthorUid(Integer userId) {
    }
}
