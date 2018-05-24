package com.web.machineversion.service;

import com.web.machineversion.model.JsonRequestBody.NewsQueryJson;

public interface UserService {
    //判断是否有资格编辑新闻
    boolean IsAbleToEditNews(Integer userId, NewsQueryJson newsQueryJson);
    //判断是否为管理员
    boolean IsAdmin(Integer userId);
}
