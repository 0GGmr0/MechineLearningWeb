package com.web.machineversion.dao;

import com.web.machineversion.model.entity.TopicMsg;
import com.web.machineversion.model.entity.TopicMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicMsgMapper {
    int countByExample(TopicMsgExample example);

    int deleteByExample(TopicMsgExample example);

    int deleteByPrimaryKey(Integer topicMsgId);

    int insert(TopicMsg record);

    int insertSelective(TopicMsg record);

    List<TopicMsg> selectByExample(TopicMsgExample example);

    TopicMsg selectByPrimaryKey(Integer topicMsgId);

    int updateByExampleSelective(@Param("record") TopicMsg record, @Param("example") TopicMsgExample example);

    int updateByExample(@Param("record") TopicMsg record, @Param("example") TopicMsgExample example);

    int updateByPrimaryKeySelective(TopicMsg record);

    int updateByPrimaryKey(TopicMsg record);
}