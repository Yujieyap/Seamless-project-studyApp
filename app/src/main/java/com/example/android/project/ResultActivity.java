package com.example.android.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.project.models.QuizQuestion;
import com.example.android.project.utilities.AppUtilities;
import com.firebase.client.Firebase;

public class ResultActivity extends AppCompatActivity {
    private static final String FIREBASE_URL = "https://crackling-heat-5434.firebaseio.com/question";
    private String selectedText = "";
    private Firebase firebaseref;
    private String selectedTexts = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Firebase.setAndroidContext(this);

        Button logout = (Button) findViewById(R.id.button31);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, Login.class));
            }
        });

        Button yousrButton = (Button) findViewById(R.id.btn__Home);

        yousrButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, StudentHomed.class));
            }
        });



        Button youButton = (Button) findViewById(R.id.btn_Feedback);

        youButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, WriteFeedback.class));
            }
        });


        Bundle c = getIntent().getExtras();

        if (c != null) {
            selectedText = c.getString("subject");
        }

        Bundle d = getIntent().getExtras();

        if (d != null) {
            selectedTexts = d.getString("chapters");
        }

//        Button yourButton = (Button) findViewById(R.id.button5);
//
//        yourButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(ResultActivity.this, ExplanationSubAlge.class));
//            }
//        });
        TextView textResult = (TextView) findViewById(R.id.textResult);
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        textResult.setText("Your score is " + " " + score + "/" + AppUtilities.getQuestionList().size() + ". Thank you for playing the quiz!");


        if (score == 10) {

        }


        Log.d("HELLOBOYs", selectedText);


    }


    public void playagain(View o) {
        Intent intent = new Intent(this, Subjects.class);
        startActivity(intent);
    }


}