package com.web.machineversion.service;

import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.entity.Notice;
import com.web.machineversion.model.jsonrequestbody.DeleteNoticeQueryJson;
import com.web.machineversion.model.jsonrequestbody.NoticeQueryJson;

import java.util.List;

public interface NoticeService {
    Result findAll();
    Result AddNotice(Integer userId, NoticeQueryJson noticeQueryJson);
    Result deleteByTitle(Integer userId, DeleteNoticeQueryJson deleteNoticeQueryJson);
}
