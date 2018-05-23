package com.web.machineversion.service;

import com.web.machineversion.model.OV.*;

import java.util.List;
import java.util.Map;

public interface NewsService {

    NewsResult getNews();

    List<MatterInfo> getMatterInfoList();

    Map<String, ArticleInfo> getArticleInfoMap();

    List<NewsInfo> getNewsInfoList();

    Result getNewsType();

}
