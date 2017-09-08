package com.example.administrator.mygitapplication;

/**
 */
public class LogisticsData {

    private String context;//内容
    private String time;//时
    private String date;

    public String getDate() {
        return date;
    }

    public LogisticsData setDate(String date) {
        this.date = date;
        return this;
    }

    public String getContext() {
        return context;
    }

    public LogisticsData setContext(String context) {
        this.context = context;
        return this;
    }


    public String getTime() {
        return time;
    }

    public LogisticsData setTime(String time) {
        this.time = time;
        return this;
    }

}
