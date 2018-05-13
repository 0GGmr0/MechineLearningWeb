package com.web.machineversion.model.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Reply {
    private Integer sequence;
    private Integer id;
    private String content;
    private Date createTime;
    private Date updateTime;
}
