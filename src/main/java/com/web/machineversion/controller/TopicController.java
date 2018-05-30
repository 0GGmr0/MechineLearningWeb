package com.web.machineversion.controller;


import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.ResultTool;
import com.web.machineversion.model.jsonrequestbody.CommentLikedQueryJson;
import com.web.machineversion.model.jsonrequestbody.CommentQueryJson;
import com.web.machineversion.model.jsonrequestbody.TopicQueryJson;
import com.web.machineversion.service.TopicService;
import com.web.machineversion.tools.JwtUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/topic")
@CrossOrigin
public class TopicController {
    @Resource
    private TopicService topicService;

    //获取当前存在的所有topic
    @RequestMapping(value = "/allTopicInfo", method = RequestMethod.GET)
    public Result getAllTopicInfo(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        Integer userId;
        if(token.isEmpty())
            userId = null;
        else
            userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.getAllTopicInfo(userId);
    }

    //获取某篇话题的详细内容
    @RequestMapping(value = "/topicDetail", method = RequestMethod.GET)
    public Result getTopicDetail(@RequestParam(value = "topicId") Integer topicId) {
        return topicService.getTopicDetail(topicId);
    }

    //获取某篇话题的comment详情
    @RequestMapping(value = "/commentDetail", method = RequestMethod.GET)
    public Result getCommentDetail(@RequestParam(value = "topicId") Integer topicId,
                                   HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        Integer userId;
        if(token.isEmpty())
            userId = null;
        else
            userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.getCommentDetail(topicId,userId);
    }

    //发布话题
    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    public Result addTopic(@RequestBody TopicQueryJson topicQueryJson,
                           HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token.isEmpty())
            return ResultTool.error("请您登录");
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.addTopic(topicQueryJson, userId);
    }

    //给话题点赞
    @RequestMapping(value = "/setTopicLiked", method = RequestMethod.POST)
    public Result serTopicLiked(@RequestBody CommentLikedQueryJson commentLikedQueryJson,
                                HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token.isEmpty())
            return ResultTool.error("请您登录");
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.setTopicLiked(commentLikedQueryJson, userId);
    }

    //给话题的某个评论（回复）点赞
    @RequestMapping(value = "/setCommentLiked", method = RequestMethod.POST)
    public Result setCommentLiked(@RequestBody CommentLikedQueryJson commentLikedQueryJson,
                                  HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token.isEmpty())
            return ResultTool.error("请您登录");
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.setCommentLiked(commentLikedQueryJson, userId);
    }

    //给某个话题进行评论（回复）
    @RequestMapping(value = "/addTopicComment", method = RequestMethod.POST)
    public Result addTopicComment(@RequestBody CommentQueryJson commentQueryJason,
                                  HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token.isEmpty())
            return ResultTool.error("请您登录");
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.addTopicComment(commentQueryJason, userId);
    }

}