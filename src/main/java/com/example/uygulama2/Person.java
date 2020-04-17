package com.example.uygulama2;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String uname;
    private String password;
    private String gender;
    private String app_mode;
    private String weight;
    private String height;
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getApp_mode() {
        return app_mode;
    }

    public void setApp_mode(String app_mode) {
        this.app_mode = app_mode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private String age;
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person(String uname, String password, String gender, String app_mode, String weight, String height, String age) {
        this.uname = uname;
        this.password = password;
        this.gender = gender;
        this.app_mode = app_mode;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }





}
