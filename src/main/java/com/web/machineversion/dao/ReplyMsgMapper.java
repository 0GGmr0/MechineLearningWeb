package com.web.machineversion.dao;

import com.web.machineversion.model.entity.ReplyMsg;
import com.web.machineversion.model.entity.ReplyMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReplyMsgMapper {
    int countByExample(ReplyMsgExample example);

    int deleteByExample(ReplyMsgExample example);

    int deleteByPrimaryKey(Integer replyMsgId);

    int insert(ReplyMsg record);

    int insertSelective(ReplyMsg record);

    List<ReplyMsg> selectByExample(ReplyMsgExample example);

    ReplyMsg selectByPrimaryKey(Integer replyMsgId);

    int updateByExampleSelective(@Param("record") ReplyMsg record, @Param("example") ReplyMsgExample example);

    int updateByExample(@Param("record") ReplyMsg record, @Param("example") ReplyMsgExample example);

    int updateByPrimaryKeySelective(ReplyMsg record);

    int updateByPrimaryKey(ReplyMsg record);
}