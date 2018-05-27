package com.web.machineversion.model.OV;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//{
//        "code":"SUCCESS",
//        "message":null,
//        "data": {
//        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNjEyMTY2NiIsImV4cCI6MTUyNzc2NzExMiwiaWF0IjoxNTI3MzM1MTEyfQ.bRBUXRrKn3A4vGg5VrpIpeaSGgBo-bRs8xxoKRUGfLKsEhzs9-3ku2xJR9-reN4XwY5LvaAy0XV8tg6Z3ac8Uw",
//        "username":"喵的之章！"
//        "uid":1,
//        "identity":3,//关于identity(身份)的枚举值，请后端约定一下并发布在本issue，劳烦！
//        "loginTimes":24//不情之请
//        }
//        }
@Data
public class LoginResultInfo {
    @JsonProperty("token")
    private String token;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("uid")
    private Integer userId;
    @JsonProperty("identity")
    private Integer userRool;
    @JsonProperty("loginTimes")
    private Integer loginTimes;
}
