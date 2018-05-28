package com.web.machineversion.service;

import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.jsonrequestbody.NoticeQueryJson;

public interface NoticeService {
    Result findAll();
    Result AddNotice(Integer userId, NoticeQueryJson noticeQueryJson);
    Result deleteByTitle(Integer userId, NoticeQueryJson deleteNoticeQueryJson);
}
