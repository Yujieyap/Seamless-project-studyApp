package com.example.android.project.models;

import java.util.ArrayList;

/**
 * Created by fypjadmin on 4/22/2016.
 */
public class QuizQuestion {

    private ArrayList<String> choices;
    private String type;
    private String question;

    private String answer;
    private String key;

    public QuizQuestion( ArrayList<String> choices, String type, String question, String answer) {
        this.choices = choices;
        this.type = type;
        this.question = question;

        this.answer = answer;
    }

    public QuizQuestion(ArrayList<String> choices, String type, String question, String answer, String key) {
        this.choices = choices;
        this.type = type;
        this.question = question;
        this.answer = answer;
        this.key = key;
    }

    public QuizQuestion(String key) {
        this.key = key;
    }

    public QuizQuestion() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
