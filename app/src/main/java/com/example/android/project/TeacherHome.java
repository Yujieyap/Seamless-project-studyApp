package com.example.android.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        Button yourButton = (Button) findViewById(R.id.button35);

        yourButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(TeacherHome.this, Profile.class));
            }
        });

        Button logout = (Button) findViewById(R.id.button31);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(TeacherHome.this, Login.class));
            }
        });



        Button yoursaButton = (Button) findViewById(R.id.button36);

        yoursaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(TeacherHome.this, Register.class));
            }
        });


        Button yoursssaButton = (Button) findViewById(R.id.button33);

        yoursssaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(TeacherHome.this, CreatingQuiz.class));
            }
        });

        Button youButton = (Button) findViewById(R.id.btn_Feeedbackk);

        youButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(TeacherHome.this, Readfb.class));
            }
        });

        Button feedback = (Button) findViewById(R.id.bt_Liveeee);

        feedback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(TeacherHome.this, ChatActivity.class));
            }
        });


    }
}
