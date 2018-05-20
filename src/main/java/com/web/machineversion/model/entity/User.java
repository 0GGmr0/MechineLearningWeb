package com.web.machineversion.model.entity;

public class User {
    private Integer id;

    private String usernmae;

    private String password;

    private Integer rool;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsernmae() {
        return usernmae;
    }

    public void setUsernmae(String usernmae) {
        this.usernmae = usernmae == null ? null : usernmae.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRool() {
        return rool;
    }

    public void setRool(Integer rool) {
        this.rool = rool;
    }
}