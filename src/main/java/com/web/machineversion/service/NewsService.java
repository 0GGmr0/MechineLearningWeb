package com.web.machineversion.service;

import com.web.machineversion.model.JsonRequestBody.NewsQueryJson;
import com.web.machineversion.model.OV.*;

import java.util.List;
import java.util.Map;

public interface NewsService {
    //获取前端要求的所有的新闻
    NewsResult getNews();
    //从数据表找到status为1的非常重要的新闻，并且把他们转换成MatterInf0返回
    List<MatterInfo> getMatterInfoList();
    //从所有新闻的，并且把他们转换成ArticleInfoMap类型返回
    Map<String, ArticleInfo> getArticleInfoMap();
    //获取所有新闻，并把他们转换成NewsInfo类型返回
    List<NewsInfo> getNewsInfoList();
    //获取新闻所有的种类
    Result getNewsType();
    //添加一条新的新闻
    Result AddNewNews(Integer userId, NewsQueryJson newsQueryJson);
    //修改一条新闻
    Result ModifyNews(Integer userId, NewsQueryJson newsQueryJson);
    //删除一条新闻，依据的是新闻Id
    Result DeleteNews(Integer userId, NewsQueryJson newsQueryJson);

}
