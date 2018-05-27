package com.web.machineversion.model.jsonrequestbody;
//{
//        type:'meeting',
//        title:'关于XXX议会',
//        content:" 今天下午14：00点全体工作员在办公室开会，请大家安排好工作，准时参加会议。\n",
//        author:"XXX 办公室",
//        time:"X年XX月XX日"
//        }

import lombok.Data;

import java.util.Date;
@Data
public class NoticeQueryJson {
    private String type;
    private String title;
    private String content;
    private String author;
    private Date time;
}
