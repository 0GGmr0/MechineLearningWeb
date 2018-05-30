package com.web.machineversion.controller;

import com.web.machineversion.model.OV.Result;
import com.web.machineversion.service.UserService;
import com.web.machineversion.tools.JwtUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/userMessage", method = RequestMethod.GET)
    public Result QueryUserMessage(HttpServletRequest httpServletRequest, Integer userId) {
        String token = httpServletRequest.getHeader("Authorization");
        if(userId == null)
            return userService.getUserMessage(Integer.parseInt(JwtUtil.parseJwt(token)));
        return userService.getUserMessage(userId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result getAllMember() { return userService.getAllMember(); }
}
