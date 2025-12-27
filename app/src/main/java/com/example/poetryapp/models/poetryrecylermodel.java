package com.example.poetryapp.models;

import com.google.gson.annotations.SerializedName;

public class poetryrecylermodel {

    private String id;

    private String PoetryData;

    private String PoetName;

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setPoetName(String poetName) {
        PoetName = poetName;
    }

    public void setPoetryData(String poetryData) {
        PoetryData = poetryData;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("date_time")
    private String dateTime;

    public String getId() {
        return id;
    }

    public String getPoetryData() {
        return PoetryData;
    }

    public String getPoetName() {
        return PoetName;
    }

    public String getDateTime() {
        return dateTime;
    }
}
