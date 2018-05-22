package com.web.machineversion.service;

import com.web.machineversion.model.OV.ArticleInfo;
import com.web.machineversion.model.OV.MatterInfo;
import com.web.machineversion.model.OV.NewsInfo;
import com.web.machineversion.model.OV.NewsResult;

import java.util.List;
import java.util.Map;

public interface NewsService {

    NewsResult getNews();

    List<MatterInfo> getMatterInfoList();

    Map<String, ArticleInfo> getArticleInfoMap();

    List<NewsInfo> getNewsInfoList();

}
