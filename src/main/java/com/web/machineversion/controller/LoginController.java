package com.web.machineversion.controller;

import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.OV.TokenResponse;
import com.web.machineversion.model.jsonrequestbody.LoginUser;
import com.web.machineversion.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/login")
@CrossOrigin("localhost")
public class LoginController {
    @Resource
    private UserService userService;

//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public Result<TokenResponse> login(@RequestBody LoginUser loginUser) {
//        return userService.login(loginUser);
//    }

}
