package com.web.machineversion.model.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private Integer rool;
}
