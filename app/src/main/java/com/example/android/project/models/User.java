package com.example.android.project.models;

/**
 * Created by Jerriel2 on 21/5/2016.
 */
public class User {
    public static String getUserType() {
        return userType;
    }

    public static String getName() {
        return name;
    }

    public static String getPass() {
        return pass;
    }

    public static String getClassname() {
        return classname;
    }

    public static String getEmail() {
        return email;
    }

    public static String userType;

    public static void setName(String name) {

        User.name = name;
    }

    public static void setPass(String pass) {
        User.pass = pass;
    }

    public static void setClassname(String classname) {
        User.classname = classname;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static void setUserType(String userType) {

        User.userType = userType;
    }

    public static String name;
    public static String pass;
    public static String classname;
    public static String email;

    public User(String userType, String name, String pass, String classname, String email) {
        this.userType = userType;
        this.name = name;
        this.pass = pass;
        this.classname = classname;
        this.email = email;
    }
}
