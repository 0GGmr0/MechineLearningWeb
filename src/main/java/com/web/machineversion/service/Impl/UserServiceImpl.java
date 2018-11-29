package com.web.machineversion.service.Impl;

import com.web.machineversion.dao.NewsMapper;
import com.web.machineversion.dao.NoticeMapper;
import com.web.machineversion.dao.TopicMapper;
import com.web.machineversion.dao.UserMapper;
import com.web.machineversion.model.OV.*;
import com.web.machineversion.model.ResultTool;
import com.web.machineversion.model.entity.*;
import com.web.machineversion.model.jsonrequestbody.LoginUser;
import com.web.machineversion.model.jsonrequestbody.NewsQueryJson;
import com.web.machineversion.model.jsonrequestbody.NoticeQueryJson;
import com.web.machineversion.model.jsonrequestbody.TopicQueryJson;
import com.web.machineversion.service.UserService;
import com.web.machineversion.tools.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private NewsMapper newsMapper;

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private NoticeMapper noticeMapper;

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
    public boolean IsAbleToEditTopic(Integer userId, TopicQueryJson topicQueryJson) {
        Integer topicId = topicQueryJson.getTopicId();
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria()
                .andTopicIdEqualTo(topicId);
        Topic topic = topicMapper.selectByExample(topicExample).get(0);
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserIdEqualTo(topic.getUserId());
        User user = userMapper.selectByExample(userExample).get(0);
        return user.getUserId().equals(userId) || IsAdmin(userId);
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
        return user.getAdmin().equals(1);
    }

    @Override
    public Result getUserMessage(Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserIdEqualTo(userId);
        List<User> userList = userMapper.selectByExampleWithBLOBs(userExample);
        if(userList.isEmpty())
            return ResultTool.error("没有此用户");
        User user = userList.get(0);
        UserMessageResult userMessageResult = new UserMessageResult();
        userMessageResult.setAvatar(user.getAvatar());
        userMessageResult.setDepartment(user.getDepartment());
        userMessageResult.setLastLoginDatetime(changeLoginTimeFormat(user));
        userMessageResult.setPhoneNumber(user.getPhone());
        userMessageResult.setRegisterDatetime(changeRegisterTimeFormat(user));
        userMessageResult.setUserIntroduction(user.getIntroduction());
        userMessageResult.setUserName(user.getUserName());
        return ResultTool.success(userMessageResult);
    }

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
                loginResultInfo.setAdmin(existedUser.getAdmin().equals(1));
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

//    @Override
//    public boolean IsQueryJsonNotContainUserId(UserQueryJson userQueryJson) {
//        return userQueryJson.getUid().toString().equals("");
//    }

    @Override
    public boolean IsAbleToDeleteNotice(Integer userId, NoticeQueryJson noticeQueryJson) {
        String noticeTitle = noticeQueryJson.getTitle();
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria()
                .andTitleEqualTo(noticeTitle);
        List<Notice> noticeList = noticeMapper.selectByExample(noticeExample);
        if(noticeList.isEmpty())
            return false;
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserIdEqualTo(noticeList.get(0).getUserId());
        User user = userMapper.selectByExample(userExample).get(0);
        return user.getUserId().equals(userId) || IsAdmin(userId);
    }

    private String backgroundTypeIntegerToString(User user){
        Integer typeInt = user.getBackground();
        switch(typeInt){
            case 1 : return "本科生";
            case 2 : return "硕士生";
            case 3 : return "博士生";
        }
        return null;
    }

    private List<Member> getTeacherInfoList() {
        //获取所有rool为2即老师成员的信息
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andRoolEqualTo(2);
        //把获取到的所有老师信息存储到list里面
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.isEmpty())
            return null;
        //把存有老师信息的list转化成MemberList
        List<Member> teacherInfoList = new ArrayList<>();
        for(User user : userList){
            Member member = new Member();
            AuthorInfo authorInfo = new AuthorInfo();
            authorInfo.setAuthorUid(user.getUserId());
            authorInfo.setAuthorName(user.getUserName());
            member.setAuthorInfo(authorInfo);
            member.setHeadshoturl(user.getAvatar());
            member.setContaction(user.getPhone());
            String[] position = new String[2];
            List<String> stringList = new ArrayList<>();
            stringList.add(backgroundTypeIntegerToString(user));
            stringList.add(user.getSchool());
            member.setPosition(stringList);
            teacherInfoList.add(member);
        }
        return teacherInfoList;
    }

    private List<Member> getMemberInfoList() {
        //获取所有rool为1即学生成员的信息
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andRoolEqualTo(1);
        //把获取到的所有学生信息存储到list里面
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.isEmpty())
            return null;
        //把存有学生信息的list转化成MemberList
        List<Member> memberInfoList = new ArrayList<>();
        for(User user : userList){
            Member member = new Member();
            AuthorInfo authorInfo = new AuthorInfo();
            authorInfo.setAuthorUid(user.getUserId());
            authorInfo.setAuthorName(user.getUserName());
            member.setAuthorInfo(authorInfo);
            member.setHeadshoturl(user.getAvatar());
            member.setContaction(user.getPhone());
            List<String> stringList = new ArrayList<>();
            stringList.add(backgroundTypeIntegerToString(user));
            stringList.add(user.getSchool());
            member.setPosition(stringList);
            memberInfoList.add(member);
        }
        return memberInfoList;
    }

    @Override
    public Result getAllMember() {
        //获取所有的成员信息（包括老师成员的信息和学生成员的信息）
        MemberResult memberResult = new MemberResult();
        //获取学生成员的信息
        memberResult.setMemberList(getMemberInfoList());
        //获取老师成员的信息
        memberResult.setTeacherList(getTeacherInfoList());
        //若获取信息成功，返回success
        return ResultTool.success(memberResult);
    }

}


