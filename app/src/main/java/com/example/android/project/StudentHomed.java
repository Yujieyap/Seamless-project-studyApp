package com.example.android.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;

import java.lang.reflect.Field;

public class StudentHomed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homed);

        Button yoursButton = (Button) findViewById(R.id.button32);

        yoursButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StudentHomed.this, Profile.class));
            }
        });

        Button yours = (Button) findViewById(R.id.btnLesson);

        yours.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StudentHomed.this, Subjects.class));
            }
        });


        Button yourssdButton = (Button) findViewById(R.id.button34);

        yourssdButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StudentHomed.this, SubjectsGrade.class));
            }
        });

        Button yourssdsButton = (Button) findViewById(R.id.button31);

        yourssdsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StudentHomed.this, Login.class));
            }
        });


        Button buttonLive = (Button) findViewById(R.id.btn_Live);

        buttonLive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StudentHomed.this, ChatActivity.class));
            }
        });


    }


}
