package com.example.android.project.models;

import java.util.ArrayList;

/**
 * Created by fypjadmin on 5/10/2016.
 */
public class Score {


    private String key;
    private String name;
    private int points;

    private String date;

    public Score(String name, int points, String date, String key) {
        this.name = name;
        this.points = points;
        this.date = date;
        this.key = key;
    }

    public Score(String name, int points, String date) {
        this.name = name;
        this.points = points;
        this.date = date;

    }

    public Score(int points, String date) {

        this.points = points;
        this.date = date;

    }

    public Score(int points) {

        this.points = points;


    }

    public Score() {
    }

    public Score(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
