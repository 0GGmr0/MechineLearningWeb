package com.web.machineversion.controller;

import com.web.machineversion.model.jsonrequestbody.UserQueryJson;
import com.web.machineversion.model.OV.UserMessageResult;
import com.web.machineversion.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin("localhost")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/userMessage", method = RequestMethod.POST)
    public UserMessageResult QueryUserMessage(@RequestBody UserQueryJson userQueryJson) {
        Integer userId = 16122131;
        return userService.getUserMessage(userId);
    }
}
