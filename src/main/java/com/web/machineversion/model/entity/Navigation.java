package com.web.machineversion.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Navigation {
    private Integer sequence;
    private Integer id;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
}
