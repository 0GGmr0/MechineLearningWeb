package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NewsResult {
    @JsonProperty("matter")
    private List<MatterInfo> matterList;
    @JsonProperty("labnews")
    private List<NewsInfo> labNewsList;
    @JsonProperty("academic")
    private List<NewsInfo> academicNewsList;
    @JsonProperty("others")
    private List<NewsInfo> ohersList;
    @JsonProperty("articles")
    private Map<String, ArticleInfo> articles;

}
