package com.web.machineversion.controller;

import com.web.machineversion.model.OV.Result;
import com.web.machineversion.service.DayTaskService;
import com.web.machineversion.service.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/dayTask")
@CrossOrigin("localhost")
public class DayTaskController {
    @Resource
    DayTaskService dayTaskService;

    //枚举当日的任务
    //前端发送activeDay数据，为当月的日期（比如今天5月26日则发送‘26’），为字符串格式。
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result getDayTask(@RequestHeader(value = "activeDay") String date) {
        return dayTaskService.getDayTask(date);
    }
}
