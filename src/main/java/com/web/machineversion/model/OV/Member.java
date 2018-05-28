package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.lang.reflect.Array;

@Data
public class Member {
    //用户名
    @JsonProperty("username")
    private String name;
    //头像
    @JsonProperty("avatar")
    private String headshoturl;

    //联系方式（电话）
    @JsonProperty("phone")
    private String contaction;

    //学历以及所属学校（background和department）
    private String[] position = new String[2];

    public String getContaction(String phone) {
        return contaction;
    }

    public void setContaction(String contaction) {
        this.contaction = contaction;
    }

    public String getHeadshoturl() {
        return headshoturl;
    }

    public void setHeadshoturl(String headshoturl) {
        this.headshoturl = headshoturl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPosition(String[] position) {
        return this.position;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }
}
