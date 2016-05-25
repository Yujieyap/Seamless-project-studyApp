package com.example.android.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.project.models.QuizQuestion;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreateQuiz extends AppCompatActivity {

    private ArrayList<QuizQuestion> quizList = new ArrayList<>();
    private List<String> chaptersList = new ArrayList<>();
    private ArrayAdapter<String> chapterAdapter;

    private static final String FIREBASE_URL = "https://crackling-heat-5434.firebaseio.com";
    private int abc = 1;
    private Firebase firebaseref;
    private Spinner chaptersSpinner, subjectSpinner;
    private Button btnNext, btnCreate;
    private EditText inputTextA, inputTextB, inputTextC, inputTextD, inputQ, inputAnswer;
    private TextView firebaseText, countText;
    boolean result = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        Firebase.setAndroidContext(this);
        firebaseref = new Firebase(FIREBASE_URL);
        countText =(TextView) findViewById(R.id.txtCount);
        Button logout = (Button) findViewById(R.id.button31);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CreateQuiz.this, Login.class));
            }
        });

        firebaseText = (TextView) findViewById(R.id.firebaseText);
        inputTextA = (EditText) findViewById(R.id.editText15);
        inputTextB = (EditText) findViewById(R.id.editText14);
        inputTextC = (EditText) findViewById(R.id.editText11);
        inputTextD = (EditText) findViewById(R.id.editText17);
        inputQ = (EditText) findViewById(R.id.editText12);
        inputAnswer = (EditText) findViewById(R.id.editText13);

        chaptersSpinner = (Spinner) findViewById(R.id.spinner_chapters);
        subjectSpinner = (Spinner) findViewById(R.id.spinner_subjects);

        btnNext = (Button) findViewById(R.id.btn_NextTo);
        btnCreate = (Button) findViewById(R.id.btn_CreateTo);

        subjectSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        // Clear Chapters ArrayList and Adapter
                        chaptersList.clear();
                        chaptersSpinner.setAdapter(null);

                        Firebase childAddRefs = firebaseref.child("type").child(subjectSpinner.getItemAtPosition(position).toString());

                        childAddRefs.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                                try {
                                    System.out.println("Data: " + dataSnapshot.getValue());
                                    JSONObject typeName = new JSONObject(dataSnapshot.getValue().toString());
                                    chaptersList.add(typeName.getString("name"));
                                    chapterAdapter = new ArrayAdapter<String>(getApplication(), R.layout.spinner_item, chaptersList);
                                    chaptersSpinner.setAdapter(chapterAdapter);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
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

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );


        chaptersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("subjects1" + subjectSpinner.getItemAtPosition(position).toString());
                System.out.println("subjects1" + chaptersSpinner.getItemAtPosition(position).toString());
                final Firebase chiladdRefff = firebaseref.child("question").child("Quiz").child("subject").child(subjectSpinner.getSelectedItem().toString()).child(chaptersSpinner.getItemAtPosition(position).toString()).child("questions");

//                                        while(result ==false) {
                chiladdRefff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        System.out.println("SEYH" + dataSnapshot.getChildrenCount());
                        firebaseText.setText((String.valueOf(dataSnapshot.getChildrenCount())));
                    }


                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
//                chiladdRefff.addChildEventListener(new ChildEventListener() {
//
//                    @Override
//                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
////                                                result = true;
//
//
//                        try {
////                            System.out.print("Count: " + String.valueOf();
//                            firebaseText.setText(null);
//                            firebaseText.setText(String.valueOf(dataSnapshot.getChildrenCount()));
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(FirebaseError firebaseError) {
//
//                    }
//                });

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputQ.getText().toString().matches("") || inputAnswer.getText().toString().matches("") || inputTextA.getText().toString().matches("") || inputTextB.getText().toString().matches("") || inputTextC.getText().toString().matches("") || inputTextD.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a question", Toast.LENGTH_SHORT).show();

                } else {
                    addQuestion();

                    System.out.println("ABCDEFGIJJ" + abc);



                }

            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg();


//                    Firebase childAddRef = firebaseref.child("question").child("Quiz").child("subject").child(subjectSpinner.getSelectedItem().toString()).child(chaptersSpinner.getSelectedItem().toString()).child("questions");
//
//                    addQuestion();
//                if(inputQ.getText().toString() == null) {
//
//                }
//                else {
//                    for (int i = 0; i < quizList.size(); i++) {
//                        childAddRef.push().setValue(quizList.get(i));
//                    }
//
//                    quizList.clear();
//                }
//


            }
        });

    }

    private void addQuestion() {
        countText.setText((String.valueOf(abc)));
        ArrayList<String> qChoices = new ArrayList<>();
        qChoices.add(inputTextA.getText().toString());
        qChoices.add(inputTextB.getText().toString());
        qChoices.add(inputTextC.getText().toString());
        qChoices.add(inputTextD.getText().toString());

        QuizQuestion q1 = new QuizQuestion(qChoices, chaptersSpinner.getSelectedItem().toString(), inputQ.getText().toString(), inputAnswer.getText().toString());

        quizList.add(q1);
        abc++;


        inputQ.setText("");
        inputTextA.setText("");
        inputTextB.setText("");
        inputTextC.setText("");
        inputTextD.setText("");
        inputAnswer.setText("");
    }

    private void gg() {
        Firebase childAddRef = firebaseref.child("question").child("Quiz").child("subject").child(subjectSpinner.getSelectedItem().toString()).child(chaptersSpinner.getSelectedItem().toString()).child("questions");


        if (inputQ.getText().toString().matches("") || inputAnswer.getText().toString().matches("") || inputTextA.getText().toString().matches("") || inputTextB.getText().toString().matches("") || inputTextC.getText().toString().matches("") || inputTextD.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "Please enter a question", Toast.LENGTH_SHORT).show();

        } else {
            addQuestion();
            for (int i = 0; i < quizList.size(); i++) {
                childAddRef.push().setValue(quizList.get(i));
            }
            abc=0;
            countText.setText(String.valueOf(0));
            quizList.clear();
        }





    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
