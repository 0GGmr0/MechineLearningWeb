package com.web.machineversion.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class News {
    private Integer sequence;
    private Integer id;
    private String title;
    private String content;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
