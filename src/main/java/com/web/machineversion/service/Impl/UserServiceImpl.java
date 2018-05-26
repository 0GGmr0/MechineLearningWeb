package com.web.machineversion.service.Impl;

import com.web.machineversion.dao.NewsMapper;
import com.web.machineversion.dao.UserMapper;
import com.web.machineversion.model.entity.News;
import com.web.machineversion.model.entity.NewsExample;
import com.web.machineversion.model.entity.User;
import com.web.machineversion.model.entity.UserExample;
import com.web.machineversion.model.jsonrequestbody.NewsQueryJson;
import com.web.machineversion.model.OV.UserMessageResult;
import com.web.machineversion.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private NewsMapper newsMapper;

    @Resource
    private UserMapper userMapper;

    //把Date类型的数据转换成String类型的2018.05.09　20:04:23
    private String changeRegisterTimeFormat(User user) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return dateFormat.format(user.getRegisterTime());
    }

    private String changeLoginTimeFormat(User user) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return dateFormat.format(user.getLastLoginTime());
    }

    @Override
    public boolean IsAbleToEditNews(Integer userId, NewsQueryJson newsQueryJson) {
        Integer newsId = newsQueryJson.getNewsId();
        NewsExample newsExample = new NewsExample();
        newsExample.createCriteria()
                .andNewsIdEqualTo(newsId);
        News news = newsMapper.selectByExample(newsExample).get(0);
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserIdEqualTo(news.getUserId());
        User user = userMapper.selectByExample(userExample).get(0);
        return user.getUserId().equals(userId) || IsAdmin(userId);
    }

    @Override
    public boolean IsAdmin(Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserIdEqualTo(userId);
        User user = userMapper.selectByExample(userExample).get(0);
        return user.getRool().equals(4);
    }

    @Override
    public UserMessageResult getUserMessage(Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserIdEqualTo(userId);
        User user = userMapper.selectByExampleWithBLOBs(userExample).get(0);
        if(user != null) {
            UserMessageResult userMessageResult = new UserMessageResult();
            userMessageResult.setAvatar(user.getAvatar());
            userMessageResult.setDepartment(user.getDepartment());
            userMessageResult.setLastLoginDatetime(changeLoginTimeFormat(user));
            userMessageResult.setPhoneNumber(user.getPhone());
            userMessageResult.setRegisterDatetime(changeRegisterTimeFormat(user));
            userMessageResult.setUserIntroduction(user.getIntroduction());
            userMessageResult.setUserName(user.getUserName());
            return userMessageResult;
        }
        else return null;
    }
}
