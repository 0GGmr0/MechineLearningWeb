package com.web.machineversion.model.jsonrequestbody;
<<<<<<< HEAD
//{
//        type:'meeting',
//        title:'关于XXX议会',
//        content:" 今天下午14：00点全体工作员在办公室开会，请大家安排好工作，准时参加会议。\n",
//        time:"X年XX月XX日"
//        }

import lombok.Data;

import java.util.Date;
=======

import lombok.Data;


>>>>>>> master
@Data
public class NoticeQueryJson {
    private String type;
    private String title;
    private String content;
<<<<<<< HEAD
    private Date time;
=======
    private String time;
>>>>>>> master
}
