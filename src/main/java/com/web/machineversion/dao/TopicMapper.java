package com.web.machineversion.dao;

import com.web.machineversion.model.entity.Topic;
import com.web.machineversion.model.entity.TopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicMapper {
    int countByExample(TopicExample example);

    int deleteByExample(TopicExample example);

    int deleteByPrimaryKey(Integer sequence);

    int insert(Topic record);

    int insertSelective(Topic record);

    List<Topic> selectByExample(TopicExample example);

    Topic selectByPrimaryKey(Integer sequence);

    int updateByExampleSelective(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByExample(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);
}