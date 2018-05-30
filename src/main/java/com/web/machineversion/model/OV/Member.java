package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Member {
    //用户名
    @JsonProperty("author")
    private AuthorInfo authorInfo;
    //头像
    @JsonProperty("avatar")
    private String headshoturl;

    //联系方式（电话）
    @JsonProperty("phone")
    private String contaction;

    //学历以及所属学校（background和department）
    @JsonProperty("position")
    private List<String> position;
}
