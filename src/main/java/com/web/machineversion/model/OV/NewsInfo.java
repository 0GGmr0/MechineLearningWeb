package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewsInfo {
    @JsonProperty("title")
    private String newsTitle;
    @JsonProperty("type")
    private String newsType;
    @JsonProperty("newsId")
    private String newsId;
    @JsonProperty("time")
    private String newsCreateTime;
    @JsonProperty("author")
    private String newsAuthor;
    @JsonProperty("imgurl")
    private String newsImageUrl;
    @JsonProperty("overview")
    private String newsOverview;
}
