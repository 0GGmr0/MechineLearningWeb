package com.web.machineversion.service;

import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.entity.Notice;
import com.web.machineversion.model.jsonrequestbody.*;
import com.web.machineversion.model.OV.UserMessageResult;

public interface UserService {
    //判断是否有资格编辑新闻
    boolean IsAbleToEditNews(Integer userId, NewsQueryJson newsQueryJson);
    //判断是否有资格删除公告
    boolean IsAbleToDeleteNotice(Integer userId, DeleteNoticeQueryJson deleteNoticeQueryJson);
    //判断是否为管理员
    boolean IsAdmin(Integer userId);
    //返回用户信息
    UserMessageResult getUserMessage(Integer userId);
    //登录
    Result login(LoginUser user);
    //判断UserQueryJson是否包含userId，如果包含返回true，不包含返回false
    boolean IsQueryJsonNotContainUserId(UserQueryJson userQueryJson);

}
