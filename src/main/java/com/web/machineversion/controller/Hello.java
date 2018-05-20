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
    public NewsResult say() {
        NewsResult newsResult = new NewsResult();
        Map<String, ArticleInfo> map = new HashMap<String, ArticleInfo>();
        map.put("123", new ArticleInfo());
        newsResult.setArticles(map);
        return newsResult;
    }
}
