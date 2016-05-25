package com.example.android.project;

/**
 * Created by Jerriel2 on 21/5/2016.
 */
import com.example.android.project.models.User;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseUpdate {

    public static void newUser(User user, Firebase firebase){
        Map<String, Object> newUser = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(user.getName());
        values.add(user.getPass());
        values.add(user.getClassname());
        values.add(user.getEmail());
        values.add(user.getUserType());
        newUser.put(user.getName(), values);
        firebase.updateChildren(newUser);
    }
}
