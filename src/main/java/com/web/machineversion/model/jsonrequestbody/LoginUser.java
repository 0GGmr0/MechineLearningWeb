package com.web.machineversion.model.jsonrequestbody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.machineversion.model.entity.User;

<<<<<<< HEAD
=======

>>>>>>> master
import java.util.Date;

public class LoginUser extends User {
    @JsonIgnore
    @Override
    public String getUserName() {
        return super.getUserName();
    }

    @JsonIgnore
    @Override
    public Integer getRool() {
        return super.getRool();
    }

    @JsonIgnore
    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @JsonIgnore
    @Override
    public String getAvatar() {
        return super.getAvatar();
    }

    @JsonIgnore
    @Override
    public String getDepartment() {
        return super.getDepartment();
    }

    @JsonIgnore
    @Override
    public Date getRegisterTime() {
        return super.getRegisterTime();
    }

    @JsonIgnore
    @Override
    public Date getLastLoginTime() {
        return super.getLastLoginTime();
    }

    @JsonIgnore
    @Override
    public Integer getLoginTimes() {
        return super.getLoginTimes();
    }

    @JsonIgnore
    @Override
    public String getSchool() {
        return super.getSchool();
    }

    @JsonIgnore
    @Override
    public Integer getBackground() {
        return super.getBackground();
    }

    @JsonIgnore
    @Override
    public String getIntroduction() {
        return super.getIntroduction();
    }
}
