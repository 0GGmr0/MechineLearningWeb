package com.web.machineversion.dao;

import com.web.machineversion.model.entity.Navigation;
import com.web.machineversion.model.entity.NavigationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NavigationMapper {
    int countByExample(NavigationExample example);

    int deleteByExample(NavigationExample example);

    int deleteByPrimaryKey(Integer sequence);

    int insert(Navigation record);

    int insertSelective(Navigation record);

    List<Navigation> selectByExampleWithBLOBs(NavigationExample example);

    List<Navigation> selectByExample(NavigationExample example);

    Navigation selectByPrimaryKey(Integer sequence);

    int updateByExampleSelective(@Param("record") Navigation record, @Param("example") NavigationExample example);

    int updateByExampleWithBLOBs(@Param("record") Navigation record, @Param("example") NavigationExample example);

    int updateByExample(@Param("record") Navigation record, @Param("example") NavigationExample example);

    int updateByPrimaryKeySelective(Navigation record);

    int updateByPrimaryKeyWithBLOBs(Navigation record);

    int updateByPrimaryKey(Navigation record);
}