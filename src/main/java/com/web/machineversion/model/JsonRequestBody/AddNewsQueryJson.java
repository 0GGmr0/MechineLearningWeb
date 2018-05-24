package com.web.machineversion.model.JsonRequestBody;

import lombok.Data;

//{
//        "title":"热烈庆祝Actooors组织成立",
//        "type":"labnews",
//        "content":"<h1>很好</h1><p>以后继续努力</p>"
//        }

@Data
public class AddNewsQueryJson {
    private String title;
    private String type;
    private String content;
}
