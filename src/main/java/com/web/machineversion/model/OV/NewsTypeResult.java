package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NewsTypeResult {
    @JsonProperty("num")
    private Integer typeNum;

    @JsonProperty("items")
    private List<NewsExplanation> newsExplanationList;
}
