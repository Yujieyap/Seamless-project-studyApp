package com.example.android.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.project.models.QuizQuestion;
import com.example.android.project.models.Score;
import com.example.android.project.utilities.AppUtilities;
import com.example.android.project.utilities.UserUitilities;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EditQuiz extends AppCompatActivity {
    private TextView tvAnswer;
    private RadioGroup rbgChoices;
    private EditText rbChoice1, rbChoice2, rbChoice3, rbChoice4, tvQuestion;
    private Button btnNext, btnUpdate, btnDelete;
    private String selectedText = "";
    public static final String FIREBASE_URL = "https://crackling-heat-5434.firebaseio.com/question";
    public Firebase firebaseref, refScore, refScore2, refQuestions, refs, refss;
    private int score = 0;

    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
    String formattedDate = sdf.format(date);
    private boolean repeated = false;


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
        setContentView(R.layout.activity_edit_quiz);
        Firebase.setAndroidContext(this);
        firebaseref = new Firebase(FIREBASE_URL);


        tvQuestion = (EditText) findViewById(R.id.editQuestions);
        tvAnswer = (TextView) findViewById(R.id.tvAnswer);


        rbChoice1 = (EditText) findViewById(R.id.rb_choice1);
        rbChoice2 = (EditText) findViewById(R.id.rb_choice2);
        rbChoice3 = (EditText) findViewById(R.id.rb_choice3);
        rbChoice4 = (EditText) findViewById(R.id.rb_choice4);


        btnNext = (Button) findViewById(R.id.btn_next);
        btnUpdate = (Button) findViewById(R.id.btn_Updated);
        btnDelete = (Button) findViewById(R.id.btn_Deleted);


        Bundle c = getIntent().getExtras();

        if (c != null) {
            selectedText = c.getString("subject");
        }
        Log.d("BYE", selectedText);

        ArrayList<QuizQuestion> qList = AppUtilities.getQuestionList();
        setText(0);
        key = AppUtilities.getQuestionList().get(count).getKey();

        UserUitilities.setUsername("dasdsadas");

        System.out.println("dsadasdsadas" + UserUitilities.getUsername());

//        refScore = firebaseref.child("Quiz").child("subject").child(selectedText).child(AppUtilities.getChapterSelected()).child("scorelist");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
//                Firebase ref = firebaseref.child("Quiz").child("subject").child(selectedText).child(AppUtilities.getChapterSelected()).child("questions").child(key);
//                Toast.makeText(getApplicationContext(), ref.toString(), Toast.LENGTH_SHORT).show();
//                ref.removeValue();
                final Firebase refs = firebaseref.child("Quiz").child("subject").child(selectedText).child(AppUtilities.getChapterSelected());
                final Firebase refss = firebaseref.child("Quiz").child("subject").child(selectedText).child(AppUtilities.getChapterSelected());
                refs.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        Firebase ref = firebaseref.child("Quiz").child("subject").child(selectedText).child(AppUtilities.getChapterSelected()).child("questions").child(AppUtilities.getKey().get(count).getKey());
                        Toast.makeText(getApplicationContext(), ref.toString(), Toast.LENGTH_SHORT).show();
                        ref.removeValue();

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < AppUtilities.getKey().size(); i++) {
                    System.out.println("HELLOMATE" + AppUtilities.getQuestionList().get(i).getKey());
                    System.out.println("HELLOMATEs" + AppUtilities.getKey().get(i).getKey());
                    AppUtilities.getScoreLists();
                    if (AppUtilities.getQuestionList().get(i).getKey().equals(AppUtilities.getKey().get(count).getKey())) {

                        Map<String, Object> question = new HashMap<String, Object>();
                        Map<String, Object> opt1 = new HashMap<String, Object>();
                        Map<String, Object> opt2 = new HashMap<String, Object>();
                        Map<String, Object> opt3 = new HashMap<String, Object>();
                        Map<String, Object> opt4 = new HashMap<String, Object>();


                        question.put("question", tvQuestion.getText().toString());
                        opt1.put("0", rbChoice1.getText().toString());
                        opt2.put("1", rbChoice2.getText().toString());
                        opt3.put("2", rbChoice3.getText().toString());
                        opt4.put("3", rbChoice4.getText().toString());


                        System.out.println("sdfdsfdsfsdfwerf" + AppUtilities.getKey().get(i).getKey());
                        refQuestions = firebaseref.child("Quiz").child("subject").child(selectedText).child(AppUtilities.getChapterSelected()).child("questions");


                        refQuestions.child(AppUtilities.getKey().get(i).getKey()).updateChildren(question);

                        refQuestions.child(AppUtilities.getKey().get(i).getKey()).child("choices").updateChildren(opt1);
                        refQuestions.child(AppUtilities.getKey().get(i).getKey()).child("choices").updateChildren(opt2);
                        refQuestions.child(AppUtilities.getKey().get(i).getKey()).child("choices").updateChildren(opt3);
                        refQuestions.child(AppUtilities.getKey().get(i).getKey()).child("choices").updateChildren(opt4);


                    }


                }
            }

        });
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


                    }

                    changePage();

                } else {
                    count++;

//                    key = AppUtilities.getQuestionList().get(count).getKey();

                    setText(count);
                }
            }
        });


    }

    private void changePage() {
        Intent intent = new Intent(EditQuiz.this, TeacherHome.class);
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

            tvQuestion.setText(AppUtilities.getQuestionList().get(position).getQuestion());
            tvAnswer.setText(AppUtilities.getQuestionList().get(position).getAnswer());

            rbChoice1.setText(AppUtilities.getQuestionList().get(position).getChoices().get(0));
            rbChoice2.setText(AppUtilities.getQuestionList().get(position).getChoices().get(1));
            rbChoice3.setText(AppUtilities.getQuestionList().get(position).getChoices().get(2));
            rbChoice4.setText(AppUtilities.getQuestionList().get(position).getChoices().get(3));


        }
    }


}
