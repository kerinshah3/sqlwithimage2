package com.example.myapplication;

public class DataProvider {

    private String userName;
    private String score;
    private String date;

    public String getUserName() {
        return userName;
    }

    public String getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public DataProvider(String userName, String score, String date) {
        this.userName = userName;
        this.score = score;
        this.date = date;
    }

}
