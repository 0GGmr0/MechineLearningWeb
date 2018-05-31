package com.web.machineversion.service.Impl;

import com.web.machineversion.model.jsonrequestbody.LoginUser;
import com.web.machineversion.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    @Resource
    private UserService userService;
    @Test
    public void login() {
        Integer userId = 16122131;
        String passWord = "12345";
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(userId);
        loginUser.setPassWord(passWord);
        userService.login(loginUser);
    }
}