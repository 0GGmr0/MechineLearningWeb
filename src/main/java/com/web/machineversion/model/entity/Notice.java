package com.web.machineversion.model.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Notice {
    private Integer id;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
}
