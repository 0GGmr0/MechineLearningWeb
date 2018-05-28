package com.web.machineversion.service.Impl;

import com.web.machineversion.dao.NewsMapper;
import com.web.machineversion.dao.UserMapper;
import com.web.machineversion.model.entity.News;
import com.web.machineversion.model.entity.NewsExample;
import com.web.machineversion.model.entity.User;
import com.web.machineversion.model.entity.UserExample;
import com.web.machineversion.model.jsonrequestbody.NewsQueryJson;
import com.web.machineversion.model.OV.*;
import com.web.machineversion.model.ResultTool;
import com.web.machineversion.service.NewsService;
import com.web.machineversion.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private UserService userService;

    @Resource
    private NewsMapper newsMapper;

    @Resource
    private UserMapper userMapper;
    /**
     *获取新闻type对应的名称
     * 1是实验室新闻
     * 2是学术新闻
     * 3是其他
     */
    private String newsTypeIntegerToString(News news) {
        Integer typeInt = news.getType();
        switch (typeInt) {
            case 1 : return "labnews";
            case 2 : return "academic";
            case 3 : return "others";
        }
        return null;
    }

    private Integer newsTypeStringToInter(String newsType) {
        if(newsType.equals("labnews")) return 1;
        if(newsType.equals("academic")) return 2;
        if(newsType.equals("others")) return 3;
        return -1;
    }


    //通过userid找到作者
    private String newsAuthor(News news) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserIdEqualTo(news.getUserId());
        List<User> userList =  userMapper.selectByExample(userExample);
        return userList.get(0).getUserName();
    }

    //把Date类型的数据转换成String类型的
    private String changeTimeFormat(News news) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(news.getUpdateTime());
    }


    @Override
    public Result getMatterInfoList() {
        //获取status是1也就是matter级别的新闻
        NewsExample newsExample = new NewsExample();
        newsExample.createCriteria()
                .andStatusEqualTo(1);
        //把通过Example获取得到matter新闻存到list里面
        List<News> newsList = newsMapper.selectByExample(newsExample);
        List<MatterInfo> matterInfoList = new ArrayList<>();
        //把news数据拼接成matterInfoList
        if(newsList != null) {
            for(News news : newsList) {
                MatterInfo matterInfo = new MatterInfo();
                matterInfo.setMatterIconClass(news.getIconClass());
                matterInfo.setMatterNewsId(news.getNewsId().toString());
                matterInfo.setMatterTitle(news.getTitle());
                matterInfo.setMatterType(newsTypeIntegerToString(news));
                matterInfoList.add(matterInfo);
            }
            return ResultTool.success(matterInfoList);
        } else {
            return ResultTool.error("没有重要新闻");
        }
    }

    @Override
    public Result getArticleInfo(Integer newsId) {

        //获取type是1也就是实验室新闻labnews
        NewsExample newsExample = new NewsExample();
        //获取到全部新闻
        newsExample.createCriteria()
                .andNewsIdEqualTo(newsId);
        //把通过Example获取得到matter新闻存到list里面
        List<News> newsList = newsMapper.selectByExampleWithBLOBs(newsExample);
        if(newsList.isEmpty())
            return ResultTool.error("请求数据有误");
        News news = newsList.get(0);
        //把news数据拼接成stringArticleInfoMap
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setEnwsAuthor(newsAuthor(news));
        articleInfo.setNewsContent(news.getContent());
        articleInfo.setNewsCreateTime(changeTimeFormat(news));
        articleInfo.setNewsTitle(news.getTitle());
        articleInfo.setNewsType(newsTypeIntegerToString(news));
        return ResultTool.success(articleInfo);
    }

    @Override
    public Result getNewsInfoList(String newsType) {
        //获取指定种类的新闻
//        String newsType = newsQueryJson.getType();
        NewsExample newsExample = new NewsExample();
        newsExample.createCriteria()
                .andTypeEqualTo(newsTypeStringToInter(newsType));
        //把通过Example获取得到matter新闻存到list里面
        List<News> newsList = newsMapper.selectByExampleWithBLOBs(newsExample);
        List<NewsInfo> newsInfoList = new ArrayList<>();

        //把news数据拼接成matterInfoList
        if(newsList.isEmpty()) {
            return ResultTool.error("请求格式有误");
        }
        for(News news : newsList) {
            NewsInfo newsInfo = new NewsInfo();
            newsInfo.setNewsAuthor(newsAuthor(news));
            newsInfo.setNewsCreateTime(changeTimeFormat(news));
            newsInfo.setNewsId(news.getNewsId().toString());
            newsInfo.setNewsImageUrl(news.getImageUrl());
            newsInfo.setNewsTitle(news.getTitle());
            newsInfo.setNewsType(newsTypeIntegerToString(news));
            newsInfo.setNewsOverview(news.getOverview());
            newsInfoList.add(newsInfo);
        }
        return ResultTool.success(newsInfoList);
    }

    @Override
    public Result getNewsType() {
        Result<NewsTypeResult> result = new Result<>();
        NewsTypeResult newsTypeResult = new NewsTypeResult();
        List<NewsExplanation> newsExplanationList = new ArrayList<>();
        for(int i = 1; i <= 3; i++) {
            NewsExplanation newsExplanation = new NewsExplanation();
            switch (i) {
                case 1 :
                    newsExplanation.setNewsChineseLabel("实验室动态");
                    newsExplanation.setNewsValue("labnews");
                    break;
                case 2 :
                    newsExplanation.setNewsChineseLabel("学界重要新闻");
                    newsExplanation.setNewsValue("academic");
                    break;
                case 3 :
                    newsExplanation.setNewsChineseLabel("其他新闻");
                    newsExplanation.setNewsValue("others");
                    break;
            }
            newsExplanationList.add(newsExplanation);
        }
        newsTypeResult.setNewsExplanationList(newsExplanationList);
        newsTypeResult.setTypeNum(3);
        return ResultTool.success(newsTypeResult);
    }

    //将新闻种类从String转换成对应数据库中的int类型
    private Integer newsTypeStringToInteger(String typeString) {
        switch (typeString){
            case "labnews" : return 1;
            case "academic" : return 2;
            case "others" : return 3;
        }
        return null;
    }

    //添加一条新的新闻
    @Override
    public Result AddNewNews(Integer userId, NewsQueryJson newsQueryJson) {

        //添加新闻的标题
        String title = newsQueryJson.getTitle();
        //添加新闻的种类
        Integer type = newsTypeStringToInteger(newsQueryJson.getType());
        //添加新闻的内容
        String content = newsQueryJson.getContent();
        //默认新闻都是非重要的
        Integer status = 2;
        //这个是殷子良要求的 天知道是啥意思
        String iconClass = "el-icon-document";

        News news = new News();
        news.setUserId(userId);
        news.setTitle(title);
        news.setType(type);
        news.setContent(content);
        news.setStatus(status);
        news.setIconClass(iconClass);

        int res = newsMapper.insert(news);
        if(res > 0) {
            return  ResultTool.success();
        } else {
            return  ResultTool.error("写入新闻失败");
        }
    }
    @Override
    public Result ModifyNews(Integer userId, NewsQueryJson newsQueryJson) {
        if(userService.IsAbleToEditNews(userId, newsQueryJson)) {
            Integer newsId = newsQueryJson.getNewsId();
            String newsTitle = newsQueryJson.getTitle();
            String originNewsType = newsQueryJson.getType();
            String newsContent = newsQueryJson.getContent();
            News upDatePart = new News();
            if (originNewsType != null)
                upDatePart.setType(newsTypeStringToInteger(originNewsType));
            if (newsId != null)
                upDatePart.setNewsId(newsId);
            if (newsTitle != null)
                upDatePart.setTitle(newsTitle);
            if (newsContent != null)
                upDatePart.setContent(newsContent);
//        NewsExample newsExample = new NewsExample();
//        newsExample.createCriteria()
//                .andNewsIdEqualTo(newsId);
            int res = newsMapper.updateByPrimaryKeySelective(upDatePart);
            if (res > 0) {
                return ResultTool.success();
            }
        }
        return  ResultTool.error("您没有权限修改新闻");
    }

    @Override
    public Result DeleteNews(Integer userId, NewsQueryJson newsQueryJson) {
        if(userService.IsAbleToEditNews(userId, newsQueryJson)) {
            Integer newsId = newsQueryJson.getNewsId();
            int res = newsMapper.deleteByPrimaryKey(newsId);
            if (res > 0) {
                return ResultTool.success();
            }
        }
        return  ResultTool.PermissionsError();
    }

}
