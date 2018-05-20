package com.web.machineversion.dao;

import com.web.machineversion.model.entity.News;
import com.web.machineversion.model.entity.NewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper {
    int countByExample(NewsExample example);

    int deleteByExample(NewsExample example);

    int deleteByPrimaryKey(Integer sequence);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExample(NewsExample example);

    News selectByPrimaryKey(Integer sequence);

    int updateByExampleSelective(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExample(@Param("record") News record, @Param("example") NewsExample example);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}