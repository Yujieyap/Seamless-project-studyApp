package com.example.android.project;

/**
 * Created by Jerriel2 on 21/5/2016.
 */
import com.firebase.client.Firebase;

public class FirebaseClass {

    public static final String FIREBASE_FB = "https://crackling-heat-5434.firebaseio.com/feedback";

    public static  final String FIREBASE_CHAT="https://crackling-heat-5434.firebaseio.com/userchat";

    public static Firebase newUser(){
        return new Firebase("https://crackling-heat-5434.firebaseio.com/user");
    }
}
