package com.web.machineversion.service.Impl;

import com.web.machineversion.dao.NewsMapper;
import com.web.machineversion.dao.UserMapper;
import com.web.machineversion.model.OV.LoginResultInfo;
import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.ResultTool;
import com.web.machineversion.model.entity.News;
import com.web.machineversion.model.entity.NewsExample;
import com.web.machineversion.model.entity.User;
import com.web.machineversion.model.entity.UserExample;
import com.web.machineversion.model.jsonrequestbody.LoginUser;
import com.web.machineversion.model.jsonrequestbody.NewsQueryJson;
import com.web.machineversion.model.OV.UserMessageResult;
import com.web.machineversion.service.UserService;
import com.web.machineversion.tools.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return user.getRool().equals(3);
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
        return null;
    }

    @Transactional(rollbackForClassName = {"Exception"}, transactionManager = "mysqlTransactionManager")
    public Result login(LoginUser user) {
        //账号密码有一个是空的就无法登录
        if (user == null || user.getUserId() == null || user.getPassWord() == null || "".equals(user.getPassWord())) {
            return ResultTool.error("账号、密码不能为空");
        }
        // 验证数据库中有没有该用户
        User existedUser = userMapper.selectByPrimaryKey(user.getUserId());
        if (existedUser != null) {
            if (existedUser.getPassWord().equals(user.getPassWord()) ) {
                //账号密码验证成功，那么生成一个token返回给前端
                LoginResultInfo loginResultInfo = new LoginResultInfo();
                loginResultInfo.setToken(JwtUtil.createJwt(user.getUserId().toString()));
                loginResultInfo.setUserName(existedUser.getUserName());
                loginResultInfo.setUserId(existedUser.getUserId());
                loginResultInfo.setUserRool(existedUser.getRool());
                //每一次登录都要把数据库的登录次数加一并且更新到数据库中
                Integer loginTimes = existedUser.getLoginTimes() + 1;
                User upDatePart = new User();
                upDatePart.setUserId(user.getUserId());
                upDatePart.setLoginTimes(loginTimes);
                userMapper.updateByPrimaryKeySelective(upDatePart);
                loginResultInfo.setLoginTimes(loginTimes);
                return ResultTool.success(loginResultInfo);
            }
            //否则返回密码错误
            return ResultTool.error("密码错误");
        }
        //如果压根没有这个用户 说明此人没有在平台上注册
        return ResultTool.error("您不是本平台用户");
    }

}


