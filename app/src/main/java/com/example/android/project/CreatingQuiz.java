package com.example.android.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreatingQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_quiz);

        Button yourButton = (Button) findViewById(R.id.btn_newquiz);

        yourButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CreatingQuiz.this, NewQuizName.class));
            }
        });

        Button logout = (Button) findViewById(R.id.button31);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CreatingQuiz.this, Login.class));
            }
        });


        Button yoursButton = (Button) findViewById(R.id.btn_existing);

        yoursButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CreatingQuiz.this, CreateQuiz.class));
            }
        });


        Button yoursdButton = (Button) findViewById(R.id.btm_Delete);

        yoursdButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CreatingQuiz.this, EditSubjects.class));
            }
        });

        Button Buttonssss = (Button) findViewById(R.id.btnGradess);

        Buttonssss.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CreatingQuiz.this, TeacherGradesSelection.class));
            }
        });
    }
}
