package com.web.machineversion.model.jsonrequestbody;

import lombok.Data;


@Data
public class LoginUser{

    private Integer userId;
    private String passWord;
    public LoginUser() {}
    public LoginUser(Integer userId, String psw) {
        setUserId(userId);
        setPassWord(psw);
    }
}
