package com.web.machineversion.service.Impl;

import com.web.machineversion.dao.*;
import com.web.machineversion.model.OV.*;
import com.web.machineversion.model.ResultTool;
import com.web.machineversion.model.entity.*;
import com.web.machineversion.model.jsonrequestbody.CommentLikedQueryJson;
import com.web.machineversion.model.jsonrequestbody.CommentQueryJson;
import com.web.machineversion.model.jsonrequestbody.TopicQueryJson;
import com.web.machineversion.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    private AuthorInfo getAuthor(Integer userId){
        AuthorInfo authorInfo = new AuthorInfo();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        User user= userMapper.selectByExample(userExample).get(0);
        authorInfo.setAuthorName(user.getUserName());
        authorInfo.setAuthorUid(user.getUserId());
        return authorInfo;
    }

    //获取用户对当前话题是否评价或点赞的信息（获取topicmsg中的commented和liked）
    private TopicMsg getTopicMsg(Topic topic, Integer userId){
        TopicMsgExample topicMsgExample = new TopicMsgExample();
        //获取的是当前登录用户对于当前话题的topicmsg信息
        topicMsgExample.createCriteria()
                .andReplyUserIdEqualTo(userId).
                andTopicIdEqualTo(topic.getTopicId());
        List<TopicMsg> topicMsgList = topicMsgMapper.selectByExample(topicMsgExample);
        //如果是空的信息,说明压根就没有
        if(topicMsgList.isEmpty()) {
            return null;
        }
        return topicMsgList.get(0);
    }

    /**
     * 判断该topic是否已经被作者评论
     * commented：回复者是否给话题评论 1评论 2没有评论
     */
    private boolean isCommented(Topic topic, Integer userId){
        TopicMsg topicMsg = getTopicMsg(topic, userId);
        if(topicMsg == null)
            return false;
        else return topicMsg.getCommented().equals(1);
    }

    /**
     * 判断该topic是否已经被作者点赞
     * liked：回复者是否给话题点赞 1点赞 2没有点赞
     */
    private boolean isTopicLiked(Topic topic, Integer userId){
        TopicMsg topicMsg = getTopicMsg(topic, userId);
        if(topicMsg == null)
            return false;
        else return topicMsg.getLiked().equals(1);
    }

    /**
     * 判断该comment是否已经被作者点赞
     * liked：回复者是否给话题点赞 1点赞 2没有点赞
     */
    private boolean isReplyLiked(Reply reply, Integer userId){
        ReplyMsgExample replyMsgExample = new ReplyMsgExample();
        //获取的是当前登录用户对于当前话题的topicmsg信息
        replyMsgExample.createCriteria()
                .andLikeUserIdEqualTo(userId)
                .andReplyIdEqualTo(reply.getReplyId());
        List<ReplyMsg> replyMsgList = replyMsgMapper.selectByExample(replyMsgExample);
        if(replyMsgList.isEmpty()) return false;
        return replyMsgList.get(0).getLikeed().equals(1);
    }

    //获取当前话题的作者头像
    private String getAuthorAvatar(Integer userId){
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserIdEqualTo(userId);
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.isEmpty())
            return null;
        return userList.get(0).getAvatar();
    }

    //获取当前回复的作者头像

    //修改Topic表中的Commentnum列
    private Boolean addTopicCommentNum(Integer topicId){
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria()
                .andTopicIdEqualTo(topicId);
        List<Topic> topicList = topicMapper.selectByExample(topicExample);
        Topic updatePart = topicList.get(0);
        Integer commentNum = updatePart.getTopicCommentNum()+1;
        updatePart.setTopicCommentNum(commentNum);
        int res = topicMapper.updateByPrimaryKeySelective(updatePart);
        return res > 0;
    }

    //添加topicMsg表中Comment信息
    private Boolean addTopicMsgComment(Integer topicId, Integer userId) {
        TopicMsgExample topicMsgExample = new TopicMsgExample();
        topicMsgExample.createCriteria()
                .andTopicIdEqualTo(topicId)
                .andReplyUserIdEqualTo(userId);
        List<TopicMsg> topicMsgList = topicMsgMapper.selectByExample(topicMsgExample);

        if(topicMsgList.isEmpty()){
            TopicMsg topicMsg = new TopicMsg();
            topicMsg.setTopicId(topicId);
            topicMsg.setReplyUserId(userId);
            topicMsg.setCommented(1);
            topicMsg.setLiked(2);
            int res = topicMsgMapper.insert(topicMsg);
            return res > 0;
        }
        else{
            TopicMsg updatePart = topicMsgList.get(0);
            updatePart.setCommented(1);
            int res = topicMsgMapper.updateByPrimaryKey(updatePart);
            return res > 0;
        }
    }

    //获取所有的话题信息
    @Override
    public Result getAllTopicInfo(Integer userId) {
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria()
                .andTopicIdIsNotNull();
        //把通过Example获取到的所有话题信息存到List中
        List<Topic> topicList = topicMapper.selectByExample(topicExample);
        List<TopicInfo> topicInfoList = new ArrayList<>();

        //把topic数据拼接成topicInfoList数据
        if(topicList.isEmpty()){
            return ResultTool.error("目前没有话题");
        }
        Collections.reverse(topicList);
        for(Topic topic : topicList){
            TopicInfo topicInfo = new TopicInfo();
            topicInfo.setTopicAuthor(getAuthor(topic.getUserId()));
            topicInfo.setCreateTime(changeTimeFormat(topic.getCreateTime()));
            topicInfo.setTopicTheme(topic.getTheme());
            topicInfo.setTopicTitle(topic.getTitle());
            topicInfo.setTopicId(String.valueOf(topic.getTopicId()));
            topicInfo.setTopicLike(topic.getTopicLikeNum());
            topicInfo.setTopicCommentNum(topic.getTopicCommentNum());
            if(userId != null)
                topicInfo.setTopicLiked(isTopicLiked(topic, userId));
            else
                topicInfo.setTopicLiked(false);
            topicInfo.setTopicCommented(isCommented(topic, topic.getUserId()));
            topicInfoList.add(topicInfo);
        }
        return ResultTool.success(topicInfoList);
    }

    //把Date类型的数据转换成String类型的
    private String changeTimeFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    //获取某篇话题的详细内容,需要传入访问话题的Id
    @Override
    public Result getTopicDetail(Integer topicId){
        TopicExample topicExample = new TopicExample();
        //把通过Example获取到的所有话题信息存到List中
        topicExample.createCriteria()
                .andTopicIdEqualTo(topicId);
        List<Topic> topicList = topicMapper.selectByExample(topicExample);
        if(topicList.isEmpty())
            return ResultTool.error("给予的topicId有误");
        Topic topic = topicList.get(0);
        TopicDetailInfo topicDetailInfo = new TopicDetailInfo();
        topicDetailInfo.setTopicTitle(topic.getTitle());
        topicDetailInfo.setTopicType(topic.getTheme());
        topicDetailInfo.setTopicAuthor(getAuthor(topic.getUserId()));
        topicDetailInfo.setAuthorAvatar(getAuthorAvatar(topic.getUserId()));
        topicDetailInfo.setDateTime(changeTimeFormat(topic.getCreateTime()));
        topicDetailInfo.setContent(topic.getContent());
        return ResultTool.success(topicDetailInfo);
    }

    //获取某篇话题的comment详情
    @Override
    public Result getCommentDetail(Integer topicId, Integer userId){
        ReplyExample replyExample = new ReplyExample();
        //通过Example获取到的所有评论存到List中
        replyExample.createCriteria()
                .andTopicIdEqualTo(topicId);
        List<Reply> replyList = replyMapper.selectByExampleWithBLOBs(replyExample);
        List<CommentInfo> commentInfoList = new ArrayList<>();

        if(replyList.isEmpty())
            return ResultTool.error("id错误或此id没有评论");
        Collections.reverse(replyList);
        for(Reply reply : replyList){
            CommentInfo commentInfo = new CommentInfo();
            commentInfo.setCommentId(reply.getReplyId());
            commentInfo.setAuthorAvatar(getAuthorAvatar(reply.getUserId()));
            commentInfo.setCommentAuthor(getAuthor(reply.getUserId()));
            commentInfo.setContent(reply.getContent());
            commentInfo.setTime(changeTimeFormat(reply.getCreateTime()));
            commentInfo.setLike(reply.getReplyLikeNum());
            if(userId != null)
                commentInfo.setLiked(isReplyLiked(reply, userId));
            else
                commentInfo.setLiked(false);
            commentInfoList.add(commentInfo);
        }
        return ResultTool.success(commentInfoList);
    }

    //发布话题
    @Override
    public Result addTopic(TopicQueryJson topicQueryJason, Integer userId){
        //话题的标题
        String title = topicQueryJason.getTitle();
        if(title.isEmpty())
            return ResultTool.error("标题不能为空");
        //话题的主题
        String type = topicQueryJason.getType();
        if(type.isEmpty())
            return ResultTool.error("种类不能为空");
        //话题的内容
        String content = topicQueryJason.getContent();
        if(content.isEmpty())
            return ResultTool.error("内容不能为空");
        if(content.length() < 50)
            return ResultTool.error("内容不能小于50");
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
        else return ResultTool.error("数据格式有误,添加失败");

    }

    //为某个话题点赞
    @Override
    public Result setTopicLiked(CommentLikedQueryJson commentLikedQueryJson, Integer userId){
        //查找topicMsg表中与用户Id和话题Id相对应的记录的liked数据元素内容
        Integer topicId = commentLikedQueryJson.getTopicId();
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria()
                .andTopicIdEqualTo(topicId);
        List<Topic> topicList = topicMapper.selectByExample(topicExample);
        if(topicList.isEmpty())
            return ResultTool.error("topicId错误");
        Topic topic = topicList.get(0);

        //判断有topicmasg有没有这个信息表，有就直接改，没有就创建一个新的
        TopicMsgExample topicMsgExample = new TopicMsgExample();
        topicMsgExample.createCriteria().
                andReplyUserIdEqualTo(userId).
                andTopicIdEqualTo(topicId);
        List<TopicMsg> topicMsgList = topicMsgMapper.selectByExample(topicMsgExample);

        if(topicMsgList.isEmpty()) {
            TopicMsg newTopicMsg = new TopicMsg();
            newTopicMsg.setLiked(1);
            newTopicMsg.setCommented(2);
            newTopicMsg.setTopicId(topicId);
            newTopicMsg.setReplyUserId(userId);
            topic.setTopicLikeNum(topic.getTopicLikeNum() + 1);
            int res = topicMsgMapper.insert(newTopicMsg);
            int res1 = topicMapper.updateByPrimaryKeySelective(topic);
            if(res > 0 && res1 > 0) return ResultTool.success();
            else return ResultTool.error("修改出错");
        } else {
            Integer liked = topicMsgList.get(0).getLiked();
            //liked:回复者是否给话题点赞 1点赞 2没有 默认是2

            if (liked == 1) {
                liked = 2;
                topic.setTopicLikeNum(topic.getTopicLikeNum() - 1);
            } else {
                liked = 1;
                topic.setTopicLikeNum(topic.getTopicLikeNum() + 1);
            }
            //将修改后的记录更新
            TopicMsg topicMsg = topicMsgList.get(0);
            topicMsg.setLiked(liked);
            //更新喜欢数

            int res = topicMsgMapper.updateByPrimaryKeySelective(topicMsg);
            int res1 = topicMapper.updateByPrimaryKeySelective(topic);
            if (res > 0 && res1 > 0) return ResultTool.success();
            else return ResultTool.error("修改出错");
        }
    }


    //true表示 topicId不对，false表示是对的
    private boolean isTopic(Integer topicId) {
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria()
                .andTopicIdEqualTo(topicId);
        List<Topic> topicList= topicMapper.selectByExample(topicExample);
        return topicList.isEmpty();
    }
    //true表示没有commentId
    private boolean isComment(Integer commentId) {
        ReplyExample replyExample = new ReplyExample();
        replyExample.createCriteria()
                .andReplyIdEqualTo(commentId);
        List<Reply> replyList= replyMapper.selectByExample(replyExample);
        return replyList.isEmpty();
    }

    //为某个评论点赞
    @Override
    public Result setCommentLiked(CommentLikedQueryJson commentLIkeQueryJason, Integer userId){
        Integer topicId = commentLIkeQueryJason.getTopicId();
        if(isTopic(topicId))
            return ResultTool.error("给予的topicId有误");
        Integer commentId = commentLIkeQueryJason.getCommentId();
        if(isComment(commentId))
            return ResultTool.error("给予的commentId有误");
        //查找replyMsg表中与用户Id和话题Id相对应的记录的liked数据元素内容
        ReplyMsgExample replyMsgExample = new ReplyMsgExample();
        replyMsgExample.createCriteria()
                .andLikeUserIdEqualTo(userId)
                .andReplyIdEqualTo(commentId);
        List<ReplyMsg> replyMsgList = replyMsgMapper.selectByExample(replyMsgExample);
        ReplyExample replyExample = new ReplyExample();
        replyExample.createCriteria()
                .andReplyIdEqualTo(commentId);
        Reply reply = replyMapper.selectByExample(replyExample).get(0);

        //若已点赞，则删除这条记录；若未点赞，则插入记录
        if(replyMsgList.isEmpty()) {
            ReplyMsg replyMsg = new ReplyMsg();
            replyMsg.setReplyId(commentId);
            replyMsg.setLikeUserId(userId);
            replyMsg.setLikeed(1);
            Reply newReply = new Reply();
            newReply.setReplyId(commentId);
            newReply.setReplyLikeNum(reply.getReplyLikeNum() + 1);
            int res = replyMsgMapper.insert(replyMsg);
            int res1 = replyMapper.updateByPrimaryKeySelective(newReply);
            if(res > 0 && res1 > 0) return ResultTool.success();
            else return ResultTool.error("点赞失败");
        }
        else{
            ReplyMsg replyMsg = replyMsgList.get(0);
            if(replyMsg.getLikeed().equals(1)) {
                reply.setReplyLikeNum(reply.getReplyLikeNum() - 1);
                replyMsg.setLikeed(2);
            } else {
                reply.setReplyLikeNum(reply.getReplyLikeNum() + 1);
                replyMsg.setLikeed(1);
            }
            int res = replyMsgMapper.updateByPrimaryKeySelective(replyMsg);
            int res1 = replyMapper.updateByPrimaryKeySelective(reply);
            if(res > 0 && res1 > 0) return ResultTool.success();
            else return ResultTool.error();
        }
    }

    //添加某个话题的评论
    @Override
    public Result addTopicComment(CommentQueryJson commentQueryJson, Integer userId){
        //需要评论的主题
        Integer topicId = commentQueryJson.getTopicId();
        if(!isTopic(topicId))
            return ResultTool.error("给予的topicId错误");
        //评论的内容
        String content = commentQueryJson.getContent();
        if(content.isEmpty())
            return ResultTool.error("评论内容不能为空");
        Reply reply = new Reply();

        reply.setTopicId(topicId);
        reply.setUserId(userId);
        reply.setContent(content);
        reply.setReplyLikeNum(0);
        int res = replyMapper.insert(reply);
        if(res > 0){
            if(addTopicCommentNum(topicId).equals(false)) return ResultTool.error("修改topic评论数有误");
            if(addTopicMsgComment(topicId, userId).equals(false)) return ResultTool.error("修改topicMsg有误");
            return ResultTool.success();
        }
        else return ResultTool.error("评论失败");

    }



}
