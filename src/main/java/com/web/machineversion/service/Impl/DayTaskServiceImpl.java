package com.web.machineversion.service.Impl;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.web.machineversion.dao.DayTaskMapper;
import com.web.machineversion.model.OV.DayTaskInfo;
import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.ResultTool;
import com.web.machineversion.model.entity.DayTask;
import com.web.machineversion.model.entity.DayTaskExample;
import com.web.machineversion.service.DayTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DayTaskServiceImpl implements DayTaskService {
   @Resource
    private DayTaskMapper dayTaskMapper;

    //枚举当日的任务
    @Override
    public Result getDayTask(String date){
        //获取当前的年和月
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String yearStr = Integer.toString(year);
        int month = calendar.get(Calendar.MONTH)+1;
        String monthStr = Integer.toString(month);
        //设置用于匹配年月日的pattern
        //设时间time的格式为yyyy-mm-dd HH:mm
        Pattern pattern = Pattern.compile(yearStr+"-"+monthStr+"-"+date+"(.*?)", Pattern.CASE_INSENSITIVE);
        //获取dayTsak表中的所有数据（虽然比较傻。。。可是我找不到其他的办法了TT）
        DayTaskExample dayTaskExample = null;
        List<DayTask> dayTaskList = dayTaskMapper.selectByExample(dayTaskExample);
        List<DayTaskInfo> dayTaskInfoList = new ArrayList<>();
        if(!dayTaskList.isEmpty()){
            for(DayTask dayTask : dayTaskList){
                //寻找与pattern匹配的记录并拼接成dayTaskInfoList
                Matcher match = pattern.matcher(dayTask.getTime());
                if(match.find()){
                    DayTaskInfo dayTaskInfo = new DayTaskInfo();
                    Pattern patternTime = Pattern.compile(yearStr+"-"+monthStr+"-"+date+"(.*?)", Pattern.CASE_INSENSITIVE);
                    //截取time中的时间（HH：mm）
                    Matcher matcher = pattern.matcher(dayTask.getTime());
                    dayTaskInfo.setTime(match.group());
                    dayTaskInfo.setContent(dayTask.getContent());
                    dayTaskInfoList.add(dayTaskInfo);
                }
            }
            return ResultTool.success(dayTaskInfoList);
        }
        else return ResultTool.error();
    }
}
