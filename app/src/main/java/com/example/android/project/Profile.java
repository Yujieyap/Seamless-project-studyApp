package com.example.android.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.project.models.User;
import com.firebase.client.Firebase;


public class Profile extends AppCompatActivity {
    TextView userName, userPassword, userClass, userEmail;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = (TextView) findViewById(R.id.username);
        userPassword = (TextView) findViewById(R.id.userpassword);
        userClass = (TextView) findViewById(R.id.userclass);
        userEmail = (TextView) findViewById(R.id.useremail);


        user = SharedPrefeence.getLoggedInUser(Profile.this);

        userName.setText("Name: " + user.getName());
        userPassword.setText("Password: " + user.getPass());
        userClass.setText("Class: " + user.getClassname());
        userEmail.setText("Email: " + user.getEmail());

    }
}

