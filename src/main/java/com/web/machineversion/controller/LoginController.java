package com.web.machineversion.controller;

import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.jsonrequestbody.LoginUser;
import com.web.machineversion.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/login")
@CrossOrigin
public class LoginController {
    @Resource
    private UserService userService;

    /**
     * @Description: 登录接口
     * @Param: [loginUser]
     * @Return: com.web.machineversion.model.OV.Result
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result login(@RequestBody LoginUser loginUser) {
        return userService.login(loginUser);
    }

}
