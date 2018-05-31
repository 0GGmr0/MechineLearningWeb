package com.web.machineversion.dao;

import com.web.machineversion.model.entity.DayTask;
import com.web.machineversion.model.entity.DayTaskExample;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Param;

import java.util.List;
=======
import java.util.List;
import org.apache.ibatis.annotations.Param;
>>>>>>> master

public interface DayTaskMapper {
    int countByExample(DayTaskExample example);

    int deleteByExample(DayTaskExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(DayTask record);

    int insertSelective(DayTask record);

    List<DayTask> selectByExampleWithBLOBs(DayTaskExample example);

    List<DayTask> selectByExample(DayTaskExample example);

    DayTask selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") DayTask record, @Param("example") DayTaskExample example);

    int updateByExampleWithBLOBs(@Param("record") DayTask record, @Param("example") DayTaskExample example);

    int updateByExample(@Param("record") DayTask record, @Param("example") DayTaskExample example);

    int updateByPrimaryKeySelective(DayTask record);

    int updateByPrimaryKeyWithBLOBs(DayTask record);

    int updateByPrimaryKey(DayTask record);
}