package com.web.machineversion.service.Impl;

import com.web.machineversion.dao.NewsMapper;
import com.web.machineversion.dao.UserMapper;
import com.web.machineversion.model.JsonRequestBody.NewsQueryJson;
import com.web.machineversion.model.entity.News;
import com.web.machineversion.model.entity.NewsExample;
import com.web.machineversion.model.entity.User;
import com.web.machineversion.model.entity.UserExample;
import com.web.machineversion.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private NewsMapper newsMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean IsAbleToEditNews(Integer userId, NewsQueryJson newsQueryJson) {
        Integer newsId = newsQueryJson.getNews();
        NewsExample newsExample = new NewsExample();
        newsExample.createCriteria()
                .andNewsIdEqualTo(newsId);
        News news = newsMapper.selectByExample(newsExample).get(0);
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdEqualTo(news.getUserId());
        User user = userMapper.selectByExample(userExample).get(0);
        return user.getId().equals(userId) || IsAdmin(userId);
    }

    @Override
    public boolean IsAdmin(Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdEqualTo(userId);
        User user = userMapper.selectByExample(userExample).get(0);
        return user.getRool().equals(3);
    }
}
