package com.example.android.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.project.models.Quiz;
import com.example.android.project.models.QuizQuestion;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class NewQuizName extends AppCompatActivity {


    private static final String FIREBASE_URL = "https://crackling-heat-5434.firebaseio.com/";
    private Firebase firebaseref;
    private boolean repeated = false;
    private ArrayList<String> chapters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz_name);
        Firebase.setAndroidContext(this);
        firebaseref = new Firebase(FIREBASE_URL);
        Button logout = (Button) findViewById(R.id.button31);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(NewQuizName.this, Login.class));
            }
        });


        final EditText inputTextd = (EditText) findViewById(R.id.btnD);
        final Spinner spinsubjd = (Spinner) findViewById(R.id.spinner_subj);


        final Button btnIns = (Button) findViewById(R.id.btn_Inserts);
        Button yoursaButton = (Button) findViewById(R.id.button41);

        yoursaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(NewQuizName.this, TeacherHome.class));
            }
        });

        spinsubjd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                chapters.clear();
                repeated = false;
                Firebase childdAddRef = firebaseref.child("type").child(spinsubjd.getItemAtPosition(position).toString());
                childdAddRef.addChildEventListener(new ChildEventListener() {
                    @Override

                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        System.out.println("sdadassadas" + dataSnapshot.child("name").getValue());

                        chapters.add(dataSnapshot.child("name").getValue().toString());
//                        if (inputTextd.getText() == dataSnapshot.child("name").getValue()) {
//
//                        }
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
        });







        btnIns.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                EditText inputText  = (EditText) findViewById(R.id.btnD);

                for(String str : chapters){
                    if(str.equals(inputTextd.getText().toString())){
                        repeated = true;
                    }
                }
                if(repeated){
                    Toast.makeText(getApplicationContext(), "Duplicated", Toast.LENGTH_SHORT).show();
                }
                else if(inputText.getText().toString().equals(""))  {
                    Toast.makeText(getApplicationContext(), "Please enter a chapter", Toast.LENGTH_SHORT).show();
                }
                else {
                    Quiz q = new Quiz(inputText.getText().toString());
                    Firebase childAddRef = firebaseref.child("type").child(spinsubjd.getSelectedItem().toString());

                    childAddRef.push().setValue(q);

                    inputText.setText("");

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(NewQuizName.this);
                    builder1.setMessage("Would you like to create questions for this quiz now?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    startActivity(new Intent(NewQuizName.this, CreateQuiz.class));


                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }






            }

        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        chapters.clear();
    }
}
