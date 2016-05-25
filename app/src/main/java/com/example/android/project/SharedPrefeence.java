package com.example.android.project;

/**
 * Created by Jerriel2 on 22/5/2016.
 */
import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.project.models.User;

public class SharedPrefeence {
    public static boolean loginUser(Context context, User users){
        SharedPreferences sharedPreferences = context.getSharedPreferences("myShared" , Context.MODE_APPEND);
        SharedPreferences.Editor test = sharedPreferences.edit();


        test.putString("type", users.getUserType());
        test.putString("name", users.getName());
        test.putString("pass", users.getPass());
        test.putString("classname", users.getClassname());
        test.putString("email", users.getEmail());



        return test.commit();

    }


    public static User getLoggedInUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("myShared", Context.MODE_PRIVATE);

        String type = sharedPreferences.getString("type", null);
        String name = sharedPreferences.getString("name", null);
        String pass = sharedPreferences.getString("pass", null);
        String classname = sharedPreferences.getString("classname", null);
        String email = sharedPreferences.getString("email", null);




        try {
            if ((type == null) || (name == null) || (pass == null)||(classname==null) || (email==null)) {
                return null;
            }
            return new User(type, name, pass,classname,email);
        } catch (Exception ignore) {
        }


        return null;
    }
}
