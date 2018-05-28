package com.web.machineversion.service.Impl;

import com.web.machineversion.dao.TopicMapper;
import com.web.machineversion.dao.UserMapper;
import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.OV.TopicAuthor;
import com.web.machineversion.model.OV.TopicInfo;
import com.web.machineversion.model.ResultTool;
import com.web.machineversion.model.entity.Topic;
import com.web.machineversion.model.entity.TopicExample;
import com.web.machineversion.model.entity.User;
import com.web.machineversion.model.entity.UserExample;
import com.web.machineversion.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Resource
    private TopicMapper topicMapper;

    @Resource
    private UserMapper userMapper;

    //获取当前话题的作者信息（主要是用户的id和name）
    private TopicAuthor getTopicAuthor(Topic topic){
        TopicAuthor topicAuthor = new TopicAuthor();
        UserExample userExample = null;
        userExample.createCriteria().andUserIdEqualTo(topic.getUserId());
        List<User> userList = userMapper.selectByExample(userExample);
        topicAuthor.getAuthorName(userList.get(0).getUserName());
        topicAuthor.getAuthorUid(userList.get(0).getUserId());
        return topicAuthor;
    }

    @Override
    public Result getAllTopicInfo(){
        //获取所有的话题信息
        TopicExample topicExample = new TopicExample();
        //把通过Example获取到的所有话题信息存到List中
        List<Topic> topicList = topicMapper.selectByExampleWithBLOBs(topicExample);
        List<TopicInfo> topicInfoList = new ArrayList<>();

        //把topic数据拼接成topicInfoList数据
        if(topicList.isEmpty()){
            return ResultTool.error();
        }
        for(Topic topic : topicList){
            TopicInfo topicInfo = new TopicInfo();
            topicInfo.getTopicAuthor(getTopicAuthor(topic));
            topicInfo.getCreateTime(topic.getCreateTime());
            topicInfo.getTopicTheme(topic.getTheme());
            topicInfo.getTopicTitle(topic.getTitle());
            topicInfo.getTopicId(topic.getTopicId());
            topicInfo.getTopicLike(topic.getTopicLikeNum());


        }
    }

}
