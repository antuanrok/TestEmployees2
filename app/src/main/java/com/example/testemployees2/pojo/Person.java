package com.example.testemployees2.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Person {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("enName")
    @Expose
    private String enName;
    @SerializedName("birthPlace")
    @Expose
    private List<BirthPlace> birthPlace;
    @SerializedName("countAwards")
    @Expose
    private int countAwards;
    @SerializedName("sex")
    @Expose
    private String sex;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public List<BirthPlace> getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(List<BirthPlace> birthPlace) {
        this.birthPlace = birthPlace;
    }

    public int getCountAwards() {
        return countAwards;
    }

    public void setCountAwards(int countAwards) {
        this.countAwards = countAwards;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
