package com.example.android.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.project.models.Score;
import com.example.android.project.utilities.AppUtilities;
import com.example.android.project.utilities.UserUitilities;
import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion, tvAnswer;
    private RadioGroup rbgChoices;
    private RadioButton rbChoice1, rbChoice2, rbChoice3, rbChoice4;
    private Button btnNext;
    private String selectedText = "";
    public static final String FIREBASE_URL = "https://crackling-heat-5434.firebaseio.com/question";
    public Firebase firebaseref, refScore, refScore2;
    private int score = 0;

    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
    String formattedDate = sdf.format(date);

    private boolean repeated = false;

    private boolean getExist(String name) {

        boolean result = false;
        for (int i = 0; i < AppUtilities.getScoreLists().size(); i++) {
            if (name.equals(AppUtilities.getScoreLists().get((i)).getName())) {
                result = true;
            } else {
                result = false;

            }

        }
        return result;
    }

    private int count = 0;

    private String choosenAnswer = "";
    private String key = "";

    boolean result = false;
    boolean callback = false;
    String done = "";
    boolean s = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        firebaseref = new Firebase(FIREBASE_URL);
        Button logout = (Button) findViewById(R.id.button31);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(QuizActivity.this, Login.class));
            }
        });


        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvAnswer = (TextView) findViewById(R.id.tvAnswer);

        rbgChoices = (RadioGroup) findViewById(R.id.rbg_choices);

        rbChoice1 = (RadioButton) findViewById(R.id.rb_choice1);
        rbChoice2 = (RadioButton) findViewById(R.id.rb_choice2);
        rbChoice3 = (RadioButton) findViewById(R.id.rb_choice3);
        rbChoice4 = (RadioButton) findViewById(R.id.rb_choice4);

        rbChoice1.setOnClickListener(rbgListener);
        rbChoice2.setOnClickListener(rbgListener);
        rbChoice3.setOnClickListener(rbgListener);
        rbChoice4.setOnClickListener(rbgListener);

        btnNext = (Button) findViewById(R.id.btn_next);
        tvAnswer.setVisibility(View.INVISIBLE);
        Bundle c = getIntent().getExtras();

        if (c != null) {
            selectedText = c.getString("subject");
        }


        Log.d("BYE", selectedText);

        setText(0);


//        UserUitilities.setUsername("fdfsfdfasssda");

        System.out.println("dsadasdsadas" + UserUitilities.getUsername());

        refScore = firebaseref.child("Quiz").child("subject").child(selectedText).child(AppUtilities.getChapterSelected()).child("scorelist");


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                childAddRef.push().setValue(q);


                if (count == AppUtilities.getQuestionList().size()) {


                    AppUtilities.getScoreLists();
                    Score q = new Score(UserUitilities.getUsername(), score, formattedDate);
                    for (int i = 0; i < AppUtilities.getScoreLists().size(); i++) {

                        refScore2 = firebaseref.child("Quiz").child("subject").child(selectedText).child(AppUtilities.getChapterSelected()).child("scorelist").child(AppUtilities.getScoreLists().get(i).getKey());

                        AppUtilities.getScoreLists().get(i);


                        final int finalI = i;
                        final int finalI1 = i;

                        System.out.println("dsadasdsadas" + (Integer.valueOf(String.valueOf(AppUtilities.getMyScore().getPoints()))));


                        if ((Integer.valueOf(String.valueOf(AppUtilities.getMyScore().getPoints()))) > score) {

                        } else if (((Integer.valueOf(String.valueOf(AppUtilities.getMyScore().getPoints()))) == score)) {

                        } else {

                            System.out.println("sdfdsfdsfsdfwerf" + AppUtilities.getScoreLists().get(i).getKey());

                            if (AppUtilities.getScoreLists().get(finalI1).getName().equals(UserUitilities.getUsername())) {
                                Map<String, Object> points = new HashMap<String, Object>();
                                Map<String, Object> date = new HashMap<String, Object>();
                                points.put("points", score);
                                date.put("date", formattedDate);
                                refScore.child(AppUtilities.getScoreLists().get(finalI1).getKey()).updateChildren(points);
                                refScore.child(AppUtilities.getScoreLists().get(finalI1).getKey()).updateChildren(date);
                            }
                        }

                    }
                    if (!getExist(UserUitilities.getUsername())) {
                        refScore.push().setValue(q);
                        AppUtilities.getScoreLists().clear();
                    }


                    changePage();

                } else {
                    if (choosenAnswer != "") {
                        if (AppUtilities.getQuestionList().get(count).getAnswer().equals(choosenAnswer)) {
                            count++;
                            score++;
                            Log.d("score", "Your score" + score);
                            setText(count);
                            choosenAnswer = "";
                        } else {
                            count++;
                            setText(count);
                            choosenAnswer = "";
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please select an answer.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    private void changePage() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        Bundle b = new Bundle();
        b.putInt("score", score); //Your score
        intent.putExtras(b); //Put your score to your next Intent
        Bundle c = new Bundle();
        c.putString("subject", selectedText);
        intent.putExtras(c);
        Bundle d = new Bundle();
        d.putString("chapters", AppUtilities.getChapterSelected());
        intent.putExtras(d);
        Log.d("HELLOBOY", AppUtilities.getChapterSelected());
        startActivity(intent);
        finish();
    }

    private void setText(int position) {

        if (position < AppUtilities.getQuestionList().size()) {

            tvQuestion.setText("Question " + (position + 1) + " : " + AppUtilities.getQuestionList().get(position).getQuestion());
            tvAnswer.setText(AppUtilities.getQuestionList().get(position).getAnswer());

            rbChoice1.setText(AppUtilities.getQuestionList().get(position).getChoices().get(0));
            rbChoice2.setText(AppUtilities.getQuestionList().get(position).getChoices().get(1));
            rbChoice3.setText(AppUtilities.getQuestionList().get(position).getChoices().get(2));
            rbChoice4.setText(AppUtilities.getQuestionList().get(position).getChoices().get(3));

            rbgChoices.clearCheck();
        }
    }

    private View.OnClickListener rbgListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.rb_choice1:
                    choosenAnswer = rbChoice1.getText().toString();
                    break;
                case R.id.rb_choice2:
                    choosenAnswer = rbChoice2.getText().toString();
                    break;
                case R.id.rb_choice3:
                    choosenAnswer = rbChoice3.getText().toString();
                    break;
                case R.id.rb_choice4:
                    choosenAnswer = rbChoice4.getText().toString();
                    break;

            }
        }
    };


}
