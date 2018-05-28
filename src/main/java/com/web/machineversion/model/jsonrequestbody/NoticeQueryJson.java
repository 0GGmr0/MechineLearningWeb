package com.web.machineversion.model.jsonrequestbody;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeQueryJson {
    private String type;
    private String title;
    private String content;
    private Date time;
}
