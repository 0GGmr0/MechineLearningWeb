package com.web.machineversion.service;

import com.web.machineversion.model.OV.Result;

public interface DayTaskService {
    //枚举当日的任务
    Result getDayTask(String date);
}
