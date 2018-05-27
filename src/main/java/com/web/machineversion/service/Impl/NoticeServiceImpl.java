package com.web.machineversion.service.Impl;

import com.web.machineversion.dao.NoticeMapper;
import com.web.machineversion.dao.UserMapper;
import com.web.machineversion.model.OV.NoticeInfo;
import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.ResultTool;
import com.web.machineversion.model.entity.*;
import com.web.machineversion.model.jsonrequestbody.DeleteNoticeQueryJson;
import com.web.machineversion.model.jsonrequestbody.NoticeQueryJson;
import com.web.machineversion.service.NoticeService;
import com.web.machineversion.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
                .andUserIdEqualTo(notice.getId());
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

        NoticeExample noticeExample = new NoticeExample();
        List<String> typeList= Arrays.asList("meeting","outing","competition");
        List<Notice> noticeList=new ArrayList<>();
        for(String type : typeList){
            noticeExample.createCriteria()
                    .andTypeEqualTo(type);
            for(Notice notice : noticeMapper.selectByExampleWithBLOBs(noticeExample)){
                Date date = new Date();
                NoticeExample example =new NoticeExample();
                NoticeExample.Criteria criteria = example.createCriteria();
                criteria.andEventTimeLessThan(date);
                noticeMapper.deleteByExample(example);
            }
            noticeList.addAll(noticeMapper.selectByExampleWithBLOBs(noticeExample));
        }
            List<NoticeInfo> noticeInfoList = new ArrayList<>();

            //把notice数据拼接成noticeInfoList
            if(noticeList.isEmpty()) {
                return ResultTool.error("请求格式有误");
            }
            for(Notice notice : noticeList) {
                NoticeInfo noticeInfo = new NoticeInfo();
                noticeInfo.setNoticeAuthor(noticeAuthor(notice));
                noticeInfo.setNoticeTime(changeTimeFormat(notice.getEventTime()));
                noticeInfo.setNoticeTitle(notice.getTitle());
                noticeInfo.setNoticeType(notice.getType());
                noticeInfo.setPublishTime(changePublishTimeFormat(notice.getCreateTime()));
                noticeInfoList.add(noticeInfo);
            }
            return ResultTool.success(noticeInfoList);


    }

    @Override
    public Result deleteByTitle(Integer userId, DeleteNoticeQueryJson deleteNoticeQueryJson) {
        if(userService.IsAbleToDeleteNotice(userId, deleteNoticeQueryJson)) {
            String noticeTitle = deleteNoticeQueryJson.getTitle();
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
        notice.setId(UserId);
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
        notice.setId(UserId);

        int res = noticeMapper.insert(notice);
        if(res > 0) {
            return  ResultTool.success();
        } else {
            return  ResultTool.error();
        }
    }

}
