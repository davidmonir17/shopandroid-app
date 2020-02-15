package com.example.tonytech.david_project;

import android.app.Application;

public class glob extends Application {
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
