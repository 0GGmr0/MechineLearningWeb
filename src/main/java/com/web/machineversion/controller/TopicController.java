package com.web.machineversion.controller;


import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.ResultTool;
import com.web.machineversion.model.jsonrequestbody.CommentLikedQueryJson;
import com.web.machineversion.model.jsonrequestbody.CommentQueryJson;
import com.web.machineversion.model.jsonrequestbody.NewsQueryJson;
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

    /**
     * @Description: 获取当前存在的所有topic
     * @Param: [httpServletRequest]
     * @Return: com.web.machineversion.model.OV.Result
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "/allTopicInfo", method = RequestMethod.GET)
    public Result getAllTopicInfo(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        Integer userId;
        if(token == null)
            userId = null;
        else
            userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.getAllTopicInfo(userId);
    }

    /**
     * @Description: 获取某篇话题的详细内容
     * @Param: [topicId]
     * @Return: com.web.machineversion.model.OV.Result
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "/topicDetail", method = RequestMethod.GET)
    public Result getTopicDetail(@RequestParam(value = "topicId") Integer topicId) {
        return topicService.getTopicDetail(topicId);
    }

    /**
     * @Description: 获取某篇话题的comment详情
     * @Param: [topicId, httpServletRequest]
     * @Return: com.web.machineversion.model.OV.Result
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "/commentDetail", method = RequestMethod.GET)
    public Result getCommentDetail(@RequestParam(value = "topicId") Integer topicId,
                                   HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        Integer userId;
        if(token == null)
            userId = null;
        else
            userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.getCommentDetail(topicId,userId);
    }

    /**
     * @Description: 发布话题
     * @Param: [topicQueryJson, httpServletRequest]
     * @Return: com.web.machineversion.model.OV.Result
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    public Result addTopic(@RequestBody TopicQueryJson topicQueryJson,
                           HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token == null)
            return ResultTool.error("请您登录");
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.addTopic(topicQueryJson, userId);
    }

    /**
     * @Description: 给话题点赞
     * @Param: [commentLikedQueryJson, httpServletRequest]
     * @Return: com.web.machineversion.model.OV.Result
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "/setTopicLiked", method = RequestMethod.POST)
    public Result serTopicLiked(@RequestBody CommentLikedQueryJson commentLikedQueryJson,
                                HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token==null)
            return ResultTool.error("请您登录");
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.setTopicLiked(commentLikedQueryJson, userId);
    }

    /**
     * @Description: 给话题的某个评论（回复）点赞
     * @Param: [commentLikedQueryJson, httpServletRequest]
     * @Return: com.web.machineversion.model.OV.Result
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "/setCommentLiked", method = RequestMethod.POST)
    public Result setCommentLiked(@RequestBody CommentLikedQueryJson commentLikedQueryJson,
                                  HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token==null)
            return ResultTool.error("请您登录");
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.setCommentLiked(commentLikedQueryJson, userId);
    }

    /**
     * @Description: 给某个话题进行评论（回复）
     * @Param:
     * @Return:
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "/addTopicComment", method = RequestMethod.POST)
    public Result addTopicComment(@RequestBody CommentQueryJson commentQueryJason,
                                  HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token==null)
            return ResultTool.error("请您登录");
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.addTopicComment(commentQueryJason, userId);
    }

    /**
     * @Description: 给某个话题进行评论（回复）
     * @Param: [topicQueryJason, httpServletRequest]
     * @Return: com.web.machineversion.model.OV.Result
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "/editTopic", method = RequestMethod.POST)
    public Result editTopic(@RequestBody TopicQueryJson topicQueryJason,
                                  HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token==null)
            return ResultTool.error("请您登录");
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.editTopic(userId,topicQueryJason);
    }

    /**
     * @Description: 删除一个话题
     * @Param: [httpServletRequest, topicQueryJason]
     * @Return: com.web.machineversion.model.OV.Result
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "/deleteTopic", method = RequestMethod.POST)
    public Result QueryDeleteTopic(HttpServletRequest httpServletRequest,
                                  @RequestBody TopicQueryJson topicQueryJason) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token==null)
            return ResultTool.error("请您登录");
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return topicService.DeleteTopic(userId, topicQueryJason);

    }

}