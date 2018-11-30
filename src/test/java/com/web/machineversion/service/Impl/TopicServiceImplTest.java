package com.web.machineversion.service.Impl;

import com.web.machineversion.model.OV.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicServiceImplTest {

    @Resource
    private TopicServiceImpl topicService;


    @Test
    public void getAllTopicInfo() {
        Integer userID = 16122131;
        Result result = topicService.getAllTopicInfo(userID);
        System.out.print(result);
    }

    @Test
    public void getTopicDetail() {
    }

    @Test
    public void getCommentDetail() {
    }

    @Test
    public void addTopic() {
    }

    @Test
    public void setTopicLiked() {
    }

    @Test
    public void setCommentLiked() {
    }

    @Test
    public void addTopicComment() {
    }

    @Test
    public void editTopic() {
    }

    @Test
    public void deleteTopic() {
    }
}