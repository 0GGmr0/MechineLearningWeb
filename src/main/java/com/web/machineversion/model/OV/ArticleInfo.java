package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ArticleInfo {
    @JsonProperty("title")
    private String newsTitle;
    @JsonProperty("type")
    private String newsType;
    @JsonProperty("author")
    private AuthorInfo enwsAuthor;
    @JsonProperty("datetime")
    private String newsCreateTime;
    @JsonProperty("content")
    private String newsContent;
}
