package com.web.machineversion.model.JsonRequestBody;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

//{
//        "title":"热烈庆祝Actooors组织成立",
//        "type":"labnews",
//        "content":"<h1>很好</h1><p>以后继续努力</p>"
//        }

@Data
public class NewsQueryJson {
//    @JSONField(name = "news")
    private Integer news;

    private String title;
    private String type;
    private String content;
}
