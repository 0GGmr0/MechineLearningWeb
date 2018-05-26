package com.web.machineversion.controller;

import com.web.machineversion.model.JsonRequestBody.NewsQueryJson;
import com.web.machineversion.model.OV.NewsResult;
import com.web.machineversion.model.OV.Result;
import com.web.machineversion.service.NewsService;
import com.web.machineversion.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/news")
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

    //添加一条新的新闻
    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public Result QueryAddNews(@RequestBody NewsQueryJson newsQueryJson) {
//        ,
//        @RequestHeader(value = "Authorization") String token
//        Integer userId = JwtUtil.parseJwt(token);
        Integer userId = 16122131;
        return newsService.AddNewNews(userId, newsQueryJson);
    }

    //修改一条新闻
    @RequestMapping(value = "/modifyNews", method = RequestMethod.POST)
    public Result QueryModifyNews(@RequestBody NewsQueryJson newsQueryJson) {
        //@RequestHeader(value = "Authorization") String token
        Integer userId = 16122131;
        return newsService.ModifyNews(userId, newsQueryJson);

    }

    //删除一条新闻
    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
    public Result QueryDeleteNews(@RequestBody NewsQueryJson newsQueryJson) {
        //@RequestHeader(value = "Authorization") String token
        Integer userId = 16121666;
        return newsService.DeleteNews(userId, newsQueryJson);

    }
}







