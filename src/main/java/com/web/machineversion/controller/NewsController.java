package com.web.machineversion.controller;

import com.web.machineversion.model.JsonRequestBody.NewsQueryJson;
import com.web.machineversion.model.OV.NewsResult;
import com.web.machineversion.model.OV.Result;
import com.web.machineversion.service.NewsService;
import com.web.machineversion.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/api/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    @Resource
    private UserService userService;

    //获取新闻
    @RequestMapping(value = "/availableNews", method = RequestMethod.GET)
    public NewsResult getMatterList() { return newsService.getNews(); }

    //获取新闻所有的种类，英文名字和对应的中文解释
    @RequestMapping(value = "/newsType", method = RequestMethod.GET)
    public Result getNewsType() { return newsService.getNewsType(); }


    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public Result QueryAddNews(@RequestBody NewsQueryJson newsQueryJson) {
//        ,
//        @RequestHeader(value = "Authorization") String token
//        Integer userId = JwtUtil.parseJwt(token);
        Integer userId = 16122131;
        return newsService.AddNewNews(userId, newsQueryJson);
    }

    @RequestMapping(value = "/modifyNews", method = RequestMethod.POST)
    public Result QueryModifyNews(@RequestBody NewsQueryJson newsQueryJson) {
        //@RequestHeader(value = "Authorization") String token
        Integer userId = 16122131;
        return newsService.ModifyNews(userId, newsQueryJson);

    }
}







