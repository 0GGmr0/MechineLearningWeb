package com.web.machineversion.controller;

import com.web.machineversion.model.OV.ArticleInfo;
import com.web.machineversion.model.OV.NewsResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Hello {
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String say() {

        return "sd";
    }
}
