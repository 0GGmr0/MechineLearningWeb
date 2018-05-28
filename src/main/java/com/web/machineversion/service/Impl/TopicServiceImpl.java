package com.web.machineversion.service.Impl;

import com.web.machineversion.dao.ReplyMapper;
import com.web.machineversion.dao.TopicMapper;
import com.web.machineversion.dao.TopicMsgMapper;
import com.web.machineversion.dao.UserMapper;
import com.web.machineversion.model.OV.*;
import com.web.machineversion.model.ResultTool;
import com.web.machineversion.model.entity.*;
import com.web.machineversion.model.jsonrequestbody.CommentLIkeQueryJason;
import com.web.machineversion.model.jsonrequestbody.CommentQueryJson;
import com.web.machineversion.model.jsonrequestbody.NewsQueryJson;
import com.web.machineversion.model.jsonrequestbody.TopicQueryJason;
import com.web.machineversion.service.TopicService;
import com.web.machineversion.service.UserService;
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

    @Resource
    private TopicMsgMapper topicMsgMapper;

    @Resource
    private ReplyMapper replyMapper;

    @Resource
    private ReplyMsgMapper replyMsgMapper;

    //获取当前话题的作者信息（主要是用户的id和name）
    private TopicAuthor getAuthor(Integer userId){
        TopicAuthor topicAuthor = new TopicAuthor();
        UserExample userExample = null;
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> userList = userMapper.selectByExample(userExample);
        topicAuthor.getAuthorName(userList.get(0).getUserName());
        topicAuthor.getAuthorUid(userList.get(0).getUserId());
        return topicAuthor;
    }

    //获取用户对当前话题是否评价或点赞的信息（获取topicmsg中的commented和liked）
    private TopicMsg getTopicMsg(Topic topic, Integer userId){
        TopicMsg topicMsg = new TopicMsg();
        TopicMsgExample topicMsgExample = null;
        //获取的是当前登录用户对于当前话题的topicmsg信息
        topicMsgExample.createCriteria().andReplyUserIdEqualTo(userId).andTopicIdEqualTo(topic.getTopicId());
        List<TopicMsg> topicMsgList = topicMsgMapper.selectByExample(topicMsgExample);
        topicMsg.getCommented(topicMsgList.get(0).getCommented());
        topicMsg.getLiked(topicMsgList.get(0).getLiked());
        return topicMsg;
    }

    /**
     * 判断该topic是否已经被作者评论
     * commented：回复者是否给话题评论 1评论 2没有评论
     */
    private boolean isCommented(Topic topic, Integer userId){
        if(getTopicMsg(topic, userId).getCommented() == 1) return true;
        else return false;
    }

    /**
     * 判断该topic是否已经被作者点赞
     * liked：回复者是否给话题点赞 1点赞 2没有点赞
     */
    private boolean isTopicLiked(Topic topic, Integer userId){
        if(getTopicMsg(topic, userId).getLiked() == 1) return true;
        else return false;
    }

    /**
     * 判断该comment是否已经被作者点赞
     * liked：回复者是否给话题点赞 1点赞 2没有点赞
     */
    private boolean isReplyLiked(Reply reply, Integer userId){
        ReplyMsg replyMsg = new ReplyMsg();
        ReplyMsgExample replyMsgExample = null;
        //获取的是当前登录用户对于当前话题的topicmsg信息
        replyMsgExample.createCriteria().andReplyUserIdEqualTo(userId).andTopicIdEqualTo(reply.getReplyId());
        List<ReplyMsg> replyMsgList = replyMsgMapper.selectByExample(replyMsgExample);
        if(replyMsgList != null) return true;
        else return false;
    }

    //获取当前话题的作者头像
    private String getAuthorAvatar(Integer userId){
        UserExample userExample = null;
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> userList = userMapper.selectByExample(userExample);
        String avatar = userList.get(0).getAvatar();
        return avatar;
    }

    //获取当前回复的作者头像

    //修改Topic表中的Commentnum列
    private Boolean addTopicCommentNum(Integer topicId){
        TopicExample topicExample = null;
        topicExample.createCriteria().andTopicIdEqualTo(topicId);
        List<Topic> topicList = topicMapper.selectByExampleWithBLOBs(topicExample);
        Topic updatePart = topicList.get(0);
        Integer commentNum = updatePart.getTopicCommentNum()+1;
        updatePart.setTopicCommentNum(commentNum);

        int res = topicMapper.updateByPrimaryKeyWithBLOBs(updatePart);
        if(res > 0) return true;
        else return false;
    }

    //添加topicMsg表中Comment信息
    private boolean addTopicMsgComment(Integer topicId, Integer userId) {
        TopicMsgExample topicMsgExample = null;
        topicMsgExample.createCriteria().andTopicIdEqualTo(topicId).andReplyUserIdEqualTo(userId);
        List<TopicMsg> topicMsgList = topicMsgMapper.selectByExample(topicMsgExample);

        if(topicMsgList != null){
            TopicMsg updatePart = topicMsgList.get(0);
            updatePart.setCommented(1);
            int res = topicMsgMapper.updateByPrimaryKey(updatePart);
            if(res > 0) return true;
            else return false;
        }
        else{
            TopicMsg topicMsg = new TopicMsg();
            topicMsg.setTopicId(topicId);
            topicMsg.setReplyUserId(userId);
            topicMsg.setCommented(1);
            topicMsg.setLiked(2);
            int res = topicMsgMapper.insert(topicMsg);
            if(res > 0) return true;
            else return false;
        }
    }

    //获取所有的话题信息
    @Override
    public Result getAllTopicInfo(Integer userId){
        TopicExample topicExample = new TopicExample();
        //把通过Example获取到的所有话题信息存到List中
        List<Topic> topicList = topicMapper.selectByExampleWithBLOBs(topicExample);
        List<TopicInfo> topicInfoList = new ArrayList<>();

        //把topic数据拼接成topicInfoList数据
        if(topicList.isEmpty()){
            return ResultTool.error();
        }
        if(topicList != null){
            for(Topic topic : topicList){
                TopicInfo topicInfo = new TopicInfo();
                topicInfo.setTopicAuthor(getAuthor(topic.getUserId()));
                topicInfo.setCreateTime(topic.getCreateTime());
                topicInfo.setTopicTheme(topic.getTheme());
                topicInfo.setTopicTitle(topic.getTitle());
                topicInfo.setTopicId(String.valueOf(topic.getTopicId()));
                topicInfo.setTopicLike(String.valueOf(topic.getTopicLikeNum()));
                topicInfo.setTopicCommentNum(topic.getTopicCommentNum());
                topicInfo.setTopicLiked(isTopicLiked(topic, userId));
                topicInfo.setTopicCommented(isCommented(topic, userId));
                topicInfoList.add(topicInfo);
            }
            return ResultTool.success(topicInfoList);
        }
        return ResultTool.error();
    }

    //获取某篇话题的详细内容,需要传入访问话题的Id
    @Override
    public Result getTopicDetail(Integer topicId){
        TopicExample topicExample = new TopicExample();
        //把通过Example获取到的所有话题信息存到List中
        topicExample.createCriteria().andTopicIdEqualTo(topicId);
        List<Topic> topicList = topicMapper.selectByExampleWithBLOBs(topicExample);
        Topic topic = topicList.get(0);
        if(topic != null){
            TopicDetailInfo topicDetailInfo = new TopicDetailInfo();
            topicDetailInfo.setTopicTitle(topic.getTitle());
            topicDetailInfo.setTopicType(topic.getTheme());
            topicDetailInfo.setTopicAuthor(getAuthor(topic.getUserId()));
            topicDetailInfo.setAuthorAvatar(getAuthorAvatar(topic.getUserId()));
            topicDetailInfo.setDatetime(topic.getCreateTime());
            topicDetailInfo.setContent(topic.getContent());
            return ResultTool.success(topicDetailInfo);
        }
        return ResultTool.error();
    }

    //获取某篇话题的comment详情
    @Override
    public Result getCommentDetail(Integer topicId, Integer userId){
        ReplyExample replyExample = null;
        //通过Example获取到的所有评论存到List中
        replyExample.createCriteria().andTopicIdEqualTo(topicId);
        List<Reply> replyList = replyMapper.selectByExampleWithBLOBs(replyExample);
        List<CommentInfo> commentInfoList = new ArrayList<>();

        if(replyList == null) return ResultTool.error();
        if(replyList != null){
            for(Reply reply : replyList){
                CommentInfo commentInfo = new CommentInfo();
                commentInfo.setCommentId(reply.getReplyId());
                commentInfo.setAuthorAvatar(getAuthorAvatar(reply.getUserId()));
                commentInfo.setCommentAuthor(getAuthor(reply.getUserId()));
                commentInfo.setContent(reply.getContent());
                commentInfo.setTime(reply.getCreateTime());
                commentInfo.setLike(reply.getReplyLikeNum());
                commentInfo.setLiked(isReplyLiked(reply, userId));
                commentInfoList.add(commentInfo);
            }
            return ResultTool.success(commentInfoList);
        }
        return ResultTool.error();
    }

    //发布话题
    @Override
    public Result addTopic(TopicQueryJason topicQueryJason, Integer userId){
        //话题的标题
        String title = topicQueryJason.getTitle();
        //话题的主题
        String type = topicQueryJason.getType();
        //话题的内容
        String content = topicQueryJason.getContent();

        Topic topic = new Topic();

        topic.setUserId(userId);
        topic.setTitle(title);
        topic.setTheme(type);
        topic.setContent(content);
        topic.setTopicCommentNum(0);
        topic.setTopicLikeNum(0);

        int res = topicMapper.insert(topic);
        if(res > 0){
            return ResultTool.success();
        }
        else return ResultTool.error();

    }

    //为某个话题点赞
    @Override
    public Result setTopicLiked(Integer topicId, Integer userId){
        //查找topicMsg表中与用户Id和话题Id相对应的记录的liked数据元素内容
        TopicMsgExample topicMsgExample = null;
        topicMsgExample.createCriteria().andReplyUserIdEqualTo(userId).andTopicIdEqualTo(topicId);
        List<TopicMsg> topicMsgList = topicMsgMapper.selectByExample(topicMsgExample);
        Integer liked = topicMsgList.get(0).getLiked();
        //liked:回复者是否给话题点赞 1点赞 2没有 默认是2
        if(liked == 1) liked = 2;
        else liked = 1;
        //将修改后的记录更新
        TopicMsg topicMsg = topicMsgList.get(0);
        topicMsg.setLiked(liked);
        int res = topicMsgMapper.updateByPrimaryKeySelective(topicMsg);
        if(res > 0) return ResultTool.success();
        else return ResultTool.error();
    }

    //为某个评论点赞
    @Override
    public Result setCommentLiked(CommentLIkeQueryJason commentLIkeQueryJason, Integer userId){
        Integer commentId = commentLIkeQueryJason.getCommentId();
        //查找replyMsg表中与用户Id和话题Id相对应的记录的liked数据元素内容
        ReplyMsgExample replyMsgExample = null;
        replyMsgExample.createCriteria().andLikeUserIdEqualTo(userId).andReplyIdEqualTo(commentId);
        List<replyMsg> replyMsgList = replyMsgMapper.selectByExample(replyMsgExample);
        //若已点赞，则删除这条记录；若未点赞，则插入记录
        if(replyMsgList == null) {
            replyMsg replyMsg = new ReplyMsg();
            replyMsg.setReplyId(commentId);
            replyMsg.setLikeUserId(userId);
            int res = replyMsgMapper.insert(replyMsg);
            if(res > 0) return ResultTool.success();
            else return ResultTool.error();
        }
        else{
            replyMsg replyMsg = replyMsgList.get(0);
            int res = replyMsgMapper.deleteByPrimaryKey(replyMsg.getReplyMegId());
            if(res > 0) return ResultTool.success();
            else return ResultTool.error();
        }
    }

    //添加某个话题的评论
    @Override
    public Result addTopicComment(CommentQueryJson commentQueryJson, Integer userId){
        //需要评论的主题
        Integer topicId = commentQueryJson.getTopicId();
        //评论的内容
        String content = commentQueryJson.getContent();

        Reply reply = new Reply();

        reply.setTopicId(topicId);
        reply.setUserId(userId);
        reply.setContent(content);

        int res = replyMapper.insert(reply);
        if(res > 0){
            if(!addTopicCommentNum(topicId)) return ResultTool.error();
            if(!addTopicMsgComment(topicId, userId)) return ResultTool.error();
            return ResultTool.success();
        }
        else return ResultTool.error();

    }



}
