package com.web.machineversion.controller;

import com.web.machineversion.model.OV.NewsResult;
import com.web.machineversion.service.Impl.NewsServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/api/news")
public class NewsController {

    @Resource
    private NewsServiceImpl newsService;

    //获取所有的matter
    @RequestMapping(value = "/availableNews", method = RequestMethod.GET)
    public NewsResult getMatterList() {
        return newsService.getNews();
    }

}







