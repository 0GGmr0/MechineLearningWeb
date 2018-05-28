package com.web.machineversion.service;

import com.web.machineversion.model.jsonrequestbody.NewsQueryJson;
import com.web.machineversion.model.OV.*;

import java.util.List;

public interface NewsService {
    //从数据表找到status为1的非常重要的新闻，并且把他们转换成result MatterInf0返回
    Result getMatterInfoList();
    //获取指定新闻序号的新闻
    Result getArticleInfo(Integer newsId);
    //从数据库找到要找的种类的新闻并且返回成result格式
    Result getNewsInfoList(String newsType);
    //获取新闻所有的种类
    Result getNewsType();
    //添加一条新的新闻
    Result AddNewNews(Integer userId, NewsQueryJson newsQueryJson);
    //修改一条新闻
    Result ModifyNews(Integer userId, NewsQueryJson newsQueryJson);
    //删除一条新闻，依据的是新闻Id
    Result DeleteNews(Integer userId, NewsQueryJson newsQueryJson);

}
