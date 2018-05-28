package com.web.machineversion.controller;

import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.jsonrequestbody.CommentLIkeQueryJason;
import com.web.machineversion.model.jsonrequestbody.CommentQueryJson;
import com.web.machineversion.model.jsonrequestbody.TopicQueryJason;
import com.web.machineversion.service.TopicService;
import com.web.machineversion.tools.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/topic")
@CrossOrigin("localhost")
public class TopicController {
    @Resource
    TopicService topicService;

    //获取当前存在的所有topic
    @RequestMapping(value = "/allTopicInfo", method = RequestMethod.GET)
    public Result getAllTopicInfo(@RequestHeader(value = "Authorization") String token) {
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.getAllTopicInfo(userId);
    }

    //获取某篇话题的详细内容
    @RequestMapping(value = "/topicDetail", method = RequestMethod.GET)
    public Result getTopicDetail(@RequestHeader(value = "topicId") Integer topicId){
        return topicService.getTopicDetail(topicId);
    }

    //获取某篇话题的comment详情
    @RequestMapping(value = "/commentDetail", method = RequestMethod.GET)
    public Result getCommentDetail(@RequestHeader(value = "topicId") Integer topicId,
                                    @RequestHeader(value = "Authorization") String token){
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.getCommentDetail(topicId, userId);
    }

    //发布话题
    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    public Result addTopic(@RequestBody TopicQueryJason topicQueryJason,
                           @RequestHeader(value = "Authorization") String token){
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.addTopic(topicQueryJason, userId);
    }

    //给话题点赞
    @RequestMapping(value = "/setTopicLiked", method = RequestMethod.POST)
    public Result serTopicLiked(@RequestHeader(value = "topicId") Integer topicId,
                           @RequestHeader(value = "Authorization") String token){
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.setTopicLiked(topicId, userId);
    }

    //给话题的某个评论（回复）点赞
    @RequestMapping(value = "/setCommentLiked", method = RequestMethod.POST)
    public Result setCommentLiked(@RequestBody CommentLIkeQueryJason commentLIkeQueryJason,
                                  @RequestHeader(value = "Authorization") String token){
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.setCommentLiked(commentLIkeQueryJason, userId);
    }

    //给某个话题进行评论（回复）
    @RequestMapping(value = "/addTopicComment", method = RequestMethod.POST)
    public Result addTopicComment(@RequestBody CommentQueryJson commentQueryJason,
                             @RequestHeader(value = "Authorization") String token){
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.addTopicComment(commentQueryJason, userId);
    }

}
