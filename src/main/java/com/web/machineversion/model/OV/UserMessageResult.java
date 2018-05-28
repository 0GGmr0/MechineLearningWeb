package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserMessageResult {
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("department")
    private String department;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("registerDatetime")
    private String registerDatetime;
    @JsonProperty("lastLoginDatetime")
    private String lastLoginDatetime;
    @JsonProperty("introduction")
    private String userIntroduction;
}
