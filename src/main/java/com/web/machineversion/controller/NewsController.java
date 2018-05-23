package com.web.machineversion.controller;

import com.web.machineversion.model.OV.NewsResult;
import com.web.machineversion.model.OV.Result;
import com.web.machineversion.service.Impl.NewsServiceImpl;
import com.web.machineversion.service.NewsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/api/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    //获取新闻
    @RequestMapping(value = "/availableNews", method = RequestMethod.GET)
    public NewsResult getMatterList() {
        return newsService.getNews();
    }

    //获取新闻所有的种类，英文名字和对应的中文解释
    @RequestMapping(value = "/NewsType", method = RequestMethod.GET)
    public Result getNewsType() { return newsService.getNewsType(); }

}







