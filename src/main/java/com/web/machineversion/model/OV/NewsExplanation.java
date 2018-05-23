package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewsExplanation {
//    "label":"学界重要新闻"
//    "value":"academic"

    //每个新闻种类的中文解释
    @JsonProperty("label")
    private String newsChineseLabel;
    //新闻的英文种类名
    @JsonProperty("value")
    private String newsValue;
}
