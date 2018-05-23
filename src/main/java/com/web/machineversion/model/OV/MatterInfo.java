package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MatterInfo {
    @JsonProperty("iconClass")
    private String matterIconClass;
    @JsonProperty("title")
    private String matterTitle;
    @JsonProperty("type")
    private String matterType;
    @JsonProperty("newsId")
    private String matterNewsId;
}
