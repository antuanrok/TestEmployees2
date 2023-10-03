package com.example.testemployees2.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.testemployees2.converters.Converter;
import com.example.testemployees2.pojo.BirthPlace;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


@Entity(tableName = "persons")
@TypeConverters(value = Converter.class)
public class Person {

    @PrimaryKey (autoGenerate = true)
    private  int id ;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("enName")
    @Expose
    private String enName;
    @SerializedName("birthPlace")
    @Expose
    private List<BirthPlace> birthPlace=null;
    @SerializedName("countAwards")
    @Expose
    private int countAwards;
    @SerializedName("sex")
    @Expose
    private String sex;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
