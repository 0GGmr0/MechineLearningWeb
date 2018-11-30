package com.web.machineversion.controller;

import com.web.machineversion.model.OV.Result;
import com.web.machineversion.model.jsonrequestbody.NoticeQueryJson;
import com.web.machineversion.service.NoticeService;
import com.web.machineversion.tools.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/notice")
@CrossOrigin
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    /**
     * @Description: 添加一条新的公告
     * @Param: [token, noticeQueryJson]
     * @Return: com.web.machineversion.model.OV.Result
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "/addNotice", method = RequestMethod.POST)
    public Result QueryAddNews(@RequestHeader(value = "Authorization") String token,
                               @RequestBody NoticeQueryJson noticeQueryJson) {

        Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return noticeService.AddNotice(userId, noticeQueryJson);
    }

    /**
     * @Description: 手动删除一条公告
     * @Param: [token, noticeQueryJson]
     * @Return: com.web.machineversion.model.OV.Result
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "/deleteNotice", method = RequestMethod.POST)
    public Result QueryDeleteNotice(@RequestHeader(value = "Authorization") String token,
                                    @RequestBody NoticeQueryJson noticeQueryJson) {
       Integer userId = Integer.parseInt(JwtUtil.parseJwt(token));
        return noticeService.deleteByTitle(userId, noticeQueryJson);
    }

    /**
     * @Description: 获取所有有效公告
     * @Param: []
     * @Return: com.web.machineversion.model.OV.Result
     * @Author: ggmr
     * @Date: 2018/11/30
     */
    @RequestMapping(value = "/allNotice", method = RequestMethod.GET)
    public Result getNotice() {
        return noticeService.findAll();
    }
}
