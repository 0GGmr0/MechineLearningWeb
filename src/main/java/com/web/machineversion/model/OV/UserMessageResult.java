package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserMessageResult {

    private String userName;
    private String avatar;
    private String department;
    private String phoneNumber;
    private String registerDatetime;
    private String lastLoginDatetime;
    @JsonProperty("introduction")
    private String userIntroduction;
}
