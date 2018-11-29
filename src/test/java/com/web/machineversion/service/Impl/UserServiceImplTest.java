package com.web.machineversion.service.Impl;

import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.jsonrequestbody.LoginUser;
import com.web.machineversion.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Test
    public void login() {
        Integer userId = 16122131;
        String passwd = "12345";
        LoginUser loginUser = new LoginUser(userId, passwd);
        UserService userService = new UserServiceImpl();
        Result res  = userService.login(loginUser);
        System.out.print(res.getMessage());
    }
}