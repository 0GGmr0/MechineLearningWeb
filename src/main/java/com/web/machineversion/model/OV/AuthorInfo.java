package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthorInfo {
    @JsonProperty("name")
    private String authorName;
    @JsonProperty("uid")
    private Integer authorUid;
}
