package com.web.machineversion.controller;

import com.web.machineversion.model.JsonRequestBody.UserQueryJson;
import com.web.machineversion.model.OV.UserMessageResult;
import com.web.machineversion.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/userMessage", method = RequestMethod.POST)
    public UserMessageResult QueryUserMessage(@RequestBody UserQueryJson userQueryJson) {
        Integer userId = 16122131;
        return userService.getUserMessage(userId);
    }
}
