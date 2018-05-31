package com.web.machineversion.service;

import com.web.machineversion.model.OV.Result;
<<<<<<< HEAD
import com.web.machineversion.model.entity.Notice;
import com.web.machineversion.model.jsonrequestbody.DeleteNoticeQueryJson;
import com.web.machineversion.model.jsonrequestbody.NoticeQueryJson;

import java.util.List;

public interface NoticeService {
    Result findAll();
    Result AddNotice(Integer userId, NoticeQueryJson noticeQueryJson);
    Result deleteByTitle(Integer userId, DeleteNoticeQueryJson deleteNoticeQueryJson);
=======
import com.web.machineversion.model.jsonrequestbody.NoticeQueryJson;

public interface NoticeService {
    Result findAll();
    Result AddNotice(Integer userId, NoticeQueryJson noticeQueryJson);
    Result deleteByTitle(Integer userId, NoticeQueryJson deleteNoticeQueryJson);
>>>>>>> master
}
