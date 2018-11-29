package com.web.machineversion.controller;

import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.jsonrequestbody.LoginUser;
import com.web.machineversion.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class LoginControllerTest {

    @Resource
    UserService userService;
    @Test
    public void login() {
        Integer userId = 16122131;
        String passwd = "12345";
        LoginUser loginUser = new LoginUser(userId, passwd);
        userService.login(loginUser);
    }
}