package com.web.machineversion.model.entity;

public class User {
    private Integer id;

    private String userName;

    private String passWord;

    private Integer rool;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public Integer getRool() {
        return rool;
    }

    public void setRool(Integer rool) {
        this.rool = rool;
    }
}