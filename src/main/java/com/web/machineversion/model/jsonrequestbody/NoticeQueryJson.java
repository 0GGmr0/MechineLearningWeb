package com.web.machineversion.model.jsonrequestbody;

import lombok.Data;


@Data
public class NoticeQueryJson {
    private String type;
    private String title;
    private String content;
    private String time;
}
