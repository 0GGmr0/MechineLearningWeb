package com.web.machineversion.controller;

import com.web.machineversion.model.OV.Result;
<<<<<<< HEAD
import com.web.machineversion.model.jsonrequestbody.DeleteNoticeQueryJson;
=======
>>>>>>> master
import com.web.machineversion.model.jsonrequestbody.NoticeQueryJson;
import com.web.machineversion.service.NoticeService;
import com.web.machineversion.tools.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/notice")
<<<<<<< HEAD
@CrossOrigin("localhost")
public class NoticeController {

=======
@CrossOrigin
public class NoticeController {
>>>>>>> master
    @Resource
    private NoticeService noticeService;

    //添加一条新的公告
    @RequestMapping(value = "/addNotice", method = RequestMethod.POST)
    public Result QueryAddNews(@RequestHeader(value = "Authorization") String token,
                               @RequestBody NoticeQueryJson noticeQueryJson) {

        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return noticeService.AddNotice(userId, noticeQueryJson);
    }
<<<<<<< HEAD
    //手动删除一条公告
    @RequestMapping(value = "/deleteNotice", method = RequestMethod.POST)
    public Result QueryDeleteNotice(@RequestHeader(value = "Authorization") String token,
                                    @RequestBody DeleteNoticeQueryJson noticeQueryJson) {
        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return noticeService.deleteByTitle(userId, noticeQueryJson);

    }

    //获取所有有效公告
    @RequestMapping(value = "/AllNotice", method = RequestMethod.GET)
    public Result getNotice() {
        return noticeService.findAll();
    }


=======

    //手动删除一条公告
    @RequestMapping(value = "/deleteNotice", method = RequestMethod.POST)
    public Result QueryDeleteNotice(@RequestHeader(value = "Authorization") String token,
                                    @RequestBody NoticeQueryJson noticeQueryJson) {
       Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return noticeService.deleteByTitle(userId, noticeQueryJson);
    }

    //获取所有有效公告
    @RequestMapping(value = "/allNotice", method = RequestMethod.GET)
    public Result getNotice() {
        return noticeService.findAll();
    }
>>>>>>> master
}
