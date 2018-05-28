package com.web.machineversion.controller;


import com.web.machineversion.model.OV.Result;
import com.web.machineversion.service.MemberService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/member")
@CrossOrigin("localhost")
public class MemberController {

    @Resource
    MemberService memberService;

    //获取所有实验室成员信息
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result getAllMember() { return memberService.getAllMember(); }
}
