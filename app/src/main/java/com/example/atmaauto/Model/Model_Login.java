package com.example.atmaauto.Model;

import com.google.gson.annotations.SerializedName;

public class Model_Login {
    @SerializedName("USERNAME")
    private String username;
    @SerializedName("PASSWORD")
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
