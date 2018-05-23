package com.web.machineversion.service.Impl;

import com.web.machineversion.dao.NewsMapper;
import com.web.machineversion.dao.UserMapper;
import com.web.machineversion.model.OV.ArticleInfo;
import com.web.machineversion.model.OV.MatterInfo;
import com.web.machineversion.model.OV.NewsInfo;
import com.web.machineversion.model.OV.NewsResult;
import com.web.machineversion.model.entity.News;
import com.web.machineversion.model.entity.NewsExample;
import com.web.machineversion.model.entity.User;
import com.web.machineversion.model.entity.UserExample;
import com.web.machineversion.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {

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
    private String newsType(News news) {
        Integer typeInt = news.getType();
        if(typeInt == 1) return "labnews";
        else if(typeInt == 2) return "academic";
        else return "others";
    }
    //通过userid找到作者
    private String newsAuthor(News news) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdEqualTo(news.getUserId());
        List<User> userList =  userMapper.selectByExample(userExample);
        return userList.get(0).getUserName();
    }

    //把Date类型的数据转换成String类型的
    private String changeTimeFormat(News news) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return dateFormat.format(news.getUpdateTime());
    }

    @Override
    public NewsResult getNews() {
        NewsResult newsResult = new NewsResult();

        //获取matter新闻
        newsResult.setMatterList(getMatterInfoList());

        //获取所有的文章
        List<NewsInfo> newsInfoList = getNewsInfoList();
        //实验室新闻labnews,即type是1
        List<NewsInfo> labNewsList = new ArrayList<>();
        //学术新闻academic，即type是2
        List<NewsInfo> academicList = new ArrayList<>();
        //其他新闻others，即type是3
        List<NewsInfo> othersList = new ArrayList<>();

        for(NewsInfo news : newsInfoList) {
            //新闻类型
            String type = news.getNewsType();
            switch (type) {
                case "labnews": labNewsList.add(news);
                case "academic": academicList.add(news);
                case "others": othersList.add(news);
            }
        }
        newsResult.setAcademicNewsList(academicList);
        newsResult.setLabNewsList(labNewsList);
        newsResult.setOhersList(othersList);
        newsResult.setArticles(getArticleInfoMap());
        return newsResult;
    }

    @Override
    public List<MatterInfo> getMatterInfoList() {
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
                matterInfo.setMatterNewsId(news.getNewsId());
                matterInfo.setMatterTitle(news.getTitle());
                matterInfo.setMatterType(newsType(news));
                matterInfoList.add(matterInfo);
            }
            return matterInfoList;
        } else {
            return null;
        }
    }

    @Override
    public Map<String, ArticleInfo> getArticleInfoMap() {

        //获取type是1也就是实验室新闻labnews
        NewsExample newsExample = new NewsExample();
        //获取到全部新闻
        newsExample.createCriteria()
                .andNewsIdIsNotNull();
        //把通过Example获取得到matter新闻存到list里面
        List<News> newsList = newsMapper.selectByExampleWithBLOBs(newsExample);
        Map<String, ArticleInfo> stringArticleInfoMap = new HashMap<>();

        //把news数据拼接成stringArticleInfoMap
        if(newsList != null) {
            for(News news : newsList) {
                ArticleInfo articleInfo = new ArticleInfo();
                articleInfo.setEnwsAuthor(newsAuthor(news));
                articleInfo.setNewsContent(news.getContent());
                articleInfo.setNewsCreateTime(changeTimeFormat(news));
                articleInfo.setNewsTitle(news.getTitle());
                articleInfo.setNewsType(newsType(news));
                stringArticleInfoMap.put(news.getNewsId().toString(), articleInfo);
            }
            return stringArticleInfoMap;
        } else {
            return null;
        }
    }

    @Override
    public List<NewsInfo> getNewsInfoList() {
        //获取所有的新闻
        NewsExample newsExample = new NewsExample();
        newsExample.createCriteria()
                .andTypeEqualTo(1);
        //把通过Example获取得到matter新闻存到list里面
        List<News> newsList = newsMapper.selectByExample(newsExample);
        List<NewsInfo> NewsInfoList = new ArrayList<>();

        //把news数据拼接成matterInfoList
        if(newsList != null) {
            for(News news : newsList) {
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.setNewsAuthor(newsAuthor(news));
                newsInfo.setNewsCreateTime(changeTimeFormat(news));
                newsInfo.setNewsId(news.getNewsId());
                newsInfo.setNewsImageUrl(news.getImageUrl());
                newsInfo.setNewsTitle(news.getTitle());
                newsInfo.setNewsType(newsType(news));
                newsInfo.setNewsOverview(news.getOverview());
                NewsInfoList.add(newsInfo);
            }
            return NewsInfoList;
        } else {
            return null;
        }
    }
}
