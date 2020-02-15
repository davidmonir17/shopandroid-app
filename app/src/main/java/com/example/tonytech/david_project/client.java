package com.example.tonytech.david_project;

public class client {
    private String  name, user, pass , gener, birth, job;

    public client(String name, String user, String pass, String gener, String birth, String job) {
        this.name = name;
        this.user = user;
        this.pass = pass;
        this.gener = gener;
        this.birth = birth;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getGener() {
        return gener;
    }

    public String getBirth() {
        return birth;
    }

    public String getJob() {
        return job;
    }
}
