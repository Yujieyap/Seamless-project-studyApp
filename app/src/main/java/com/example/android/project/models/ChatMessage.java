package com.example.android.project.models;

/**
 * Created by Jerriel2 on 22/5/2016.
 */
public class ChatMessage {
    private String name;
    private String text;

    public ChatMessage() {

    }
    public ChatMessage(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}

