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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Override
    public Result findAll() {
        //删除全部过期的通知
        Date date = new Date();
        NoticeExample example =new NoticeExample();
        example.createCriteria()
            .andEventTimeLessThan(date);
        noticeMapper.deleteByExample(example);

        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria()
                .andNoticeIdIsNotNull();
        List<Notice> noticeList = noticeMapper.selectByExample(noticeExample);
        List<NoticeInfo> noticeInfoList = new ArrayList<>();
        //把notice数据拼接成noticeInfoList
        if(noticeList.isEmpty()) {
            return ResultTool.error("请求格式有误");
        }
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
            }
        }
        return  ResultTool.PermissionsError();
    }
    @Override
    //增加
    public Result AddNotice(Integer UserId, NoticeQueryJson noticeQueryJson) {

        Notice notice = new Notice();
        notice.setUserId(UserId);
        //添加标题
        String title = noticeQueryJson.getTitle();
        //添加种类
        String type = noticeQueryJson.getType();
        //添加内容
        String content = noticeQueryJson.getContent();
        //添加发布者
        String author = noticeAuthor(notice);
        //添加时间
        Date time = noticeQueryJson.getTime();

        notice.setTitle(title);
        notice.setType(type);
        notice.setContent(content);
        notice.setUserId(UserId);

        int res = noticeMapper.insert(notice);
        if(res > 0) {
            return  ResultTool.success();
        } else {
            return  ResultTool.error();
        }
    }
}
