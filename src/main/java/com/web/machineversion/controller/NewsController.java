package com.web.machineversion.controller;

import com.web.machineversion.model.jsonrequestbody.NewsQueryJson;
import com.web.machineversion.model.OV.Result;
import com.web.machineversion.service.NewsService;
import com.web.machineversion.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/news")
@CrossOrigin("localhost")
public class NewsController {

    @Resource
    private NewsService newsService;

    @Resource
    private UserService userService;

    //获取重要新闻
    @RequestMapping(value = "/matterNews", method = RequestMethod.GET)
    public Result getMatterList() { return newsService.getMatterInfoList(); }

    //获取指定种类的新闻
    @RequestMapping(value = "/assignationNews", method = RequestMethod.GET)
    public Result getNews(@RequestHeader(value = "type") String newsType) {
        return newsService.getNewsInfoList(newsType);
    }

    //获取指定新闻Id的新闻
    @RequestMapping(value = "/assignationArticle", method = RequestMethod.GET)
    public Result getArticle(@RequestHeader(value = "newsId") Integer newsId) {
        return newsService.getArticleInfo(newsId);
    }

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







