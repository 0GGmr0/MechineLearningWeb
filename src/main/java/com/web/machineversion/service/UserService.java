package com.web.machineversion.service;

import com.web.machineversion.model.JsonRequestBody.NewsQueryJson;
import com.web.machineversion.model.OV.UserMessageResult;

public interface UserService {
    //判断是否有资格编辑新闻
    boolean IsAbleToEditNews(Integer userId, NewsQueryJson newsQueryJson);
    //判断是否为管理员
    boolean IsAdmin(Integer userId);
    //返回用户信息
    UserMessageResult getUserMessage(Integer userId);
}
