package com.web.machineversion.controller;

import com.web.machineversion.model.OV.NewsResult;
import com.web.machineversion.service.Impl.NewsServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/news")
public class NewsController {

    @Resource
    private NewsServiceImpl newsService;

    //获取所有的matter
    @RequestMapping(value = "/availableNews", method = RequestMethod.GET)
    public NewsResult getMatterList(@RequestParam Date startDate, @RequestParam Date endDate) {
        NewsResult newsResult = new NewsResult();
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyyMMDDHHmm");
        Long startTime = Long.parseLong(simpleFormatter.format(startDate));
        Long endTime = Long.parseLong(simpleFormatter.format(endDate));


        return newsResult;
    }

}







