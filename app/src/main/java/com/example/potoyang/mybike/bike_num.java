package com.example.potoyang.mybike;

import cn.bmob.v3.BmobObject;

/**
 * Created by YYC on 2016/8/27.
 */
public class bike_num extends BmobObject {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
