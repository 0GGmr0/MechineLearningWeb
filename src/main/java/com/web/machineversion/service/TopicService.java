package com.web.machineversion.service;

import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.jsonrequestbody.CommentLIkeQueryJason;
import com.web.machineversion.model.jsonrequestbody.CommentQueryJson;
import com.web.machineversion.model.jsonrequestbody.TopicQueryJason;

public interface TopicService {
    Result getAllTopicInfo();

    //获取所有的话题信息
    Result getAllTopicInfo(Integer userId);

    //获取某篇话题的详细内容,需要传入访问话题的Id
    Result getTopicDetail(Integer topicId);

    //获取某篇话题的comment详情
    Result getCommentDetail(Integer topicId, Integer userId);

    //发布话题
    Result addTopic(TopicQueryJason topicQueryJason, Integer userId);

    //为某个话题点赞
    Result setTopicLiked(Integer topicId, Integer userId);

    //为某个评论点赞
    Result setCommentLiked(CommentLIkeQueryJason commentLIkeQueryJason, Integer userId);

    //添加某个话题的评论
    Result addTopicComment(CommentQueryJson commentQueryJson, Integer userId);
}
