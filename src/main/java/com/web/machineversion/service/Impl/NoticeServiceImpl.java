package com.web.machineversion.service.Impl;

import com.web.machineversion.dao.NoticeMapper;
import com.web.machineversion.dao.UserMapper;
import com.web.machineversion.model.OV.NoticeInfo;
import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.ResultTool;
import com.web.machineversion.model.entity.Notice;
import com.web.machineversion.model.entity.NoticeExample;
import com.web.machineversion.model.entity.User;
import com.web.machineversion.model.entity.UserExample;
import com.web.machineversion.model.jsonrequestbody.NoticeQueryJson;
import com.web.machineversion.service.NoticeService;
import com.web.machineversion.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

    //通过userId找到作者
    private String noticeAuthor(Notice notice) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserIdEqualTo(notice.getUserId());
        List<User> userList =  userMapper.selectByExample(userExample);
        return userList.get(0).getUserName();
    }

    //把Date类型的数据转换成String类型的
    private String changeTimeFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    private String changePublishTimeFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    //把String类型的时间数据转换成Date类型
    private Date changeStringToDate(String stringDate) {
        Date date = new Date();
        //注意format的格式要与日期String的格式相匹配
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            return sdf.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Result findAll() {
        //先找到所有新闻
        Date date = new Date();
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria()
                .andNoticeIdIsNotNull()
                .andEventTimeGreaterThan(date);
        List<Notice> noticeList = noticeMapper.selectByExample(noticeExample);
        List<NoticeInfo> noticeInfoList = new ArrayList<>();
        //把notice数据拼接成noticeInfoList
        if(noticeList.isEmpty()) {
            return ResultTool.error("请求格式有误");
        }
        Collections.reverse(noticeList);
        for(Notice notice : noticeList) {
            NoticeInfo noticeInfo = new NoticeInfo();
            noticeInfo.setNoticeType(notice.getType());
            noticeInfo.setNoticeTitle(notice.getTitle());
            noticeInfo.setNoticeContent(notice.getContent());
            noticeInfo.setNoticeAuthor(noticeAuthor(notice));
            noticeInfo.setNoticeTime(changeTimeFormat(notice.getEventTime()));
            noticeInfo.setPublishTime(changePublishTimeFormat(notice.getCreateTime()));
            noticeInfoList.add(noticeInfo);
        }
        return ResultTool.success(noticeInfoList);


    }

    @Override
    public Result deleteByTitle(Integer userId, NoticeQueryJson noticeQueryJson) {
        if(userService.IsAbleToDeleteNotice(userId, noticeQueryJson)) {
            String noticeTitle = noticeQueryJson.getTitle();
            NoticeExample example =new NoticeExample();
            NoticeExample.Criteria criteria = example.createCriteria();
            criteria.andTitleEqualTo(noticeTitle);
            int res = noticeMapper.deleteByExample(example);
            if (res > 0) {
                return ResultTool.success();
            } else {
                return ResultTool.error("删除出错");
            }
        }
        return  ResultTool.error("对不起，您没有权限或文章题目有错");
    }
    @Override
    //增加
    public Result AddNotice(Integer userId, NoticeQueryJson noticeQueryJson) {
        if(userService.IsAdmin(userId)) {
            //添加标题
            String title = noticeQueryJson.getTitle();
            //添加种类
            String type = noticeQueryJson.getType();
            //添加内容
            String content = noticeQueryJson.getContent();
            //添加时间
            Date time = changeStringToDate(noticeQueryJson.getTime());
            Notice notice = new Notice();
            notice.setUserId(userId);
            notice.setTitle(title);
            notice.setType(type);
            notice.setContent(content);
            notice.setEventTime(time);
            int res = noticeMapper.insert(notice);
            if(res > 0) {
                return  ResultTool.success();
            } else {
                return  ResultTool.error("插入数据格式有误");
            }
        }
        return ResultTool.PermissionsError();

    }
}
