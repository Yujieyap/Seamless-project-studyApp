package com.example.android.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Selection;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.project.Comparator.ScoreComparator;
import com.example.android.project.models.Score;
import com.example.android.project.utilities.AppUtilities;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.utilities.Utilities;

import java.util.ArrayList;
import java.util.Collections;

public class TeacherGradesSelection extends AppCompatActivity {
    private Spinner spSubject, spChapters;
    private Button btnNext;
    private ArrayList<String> chapters = new ArrayList<>();
    private Firebase ref, refChapter, refHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_grades_selection);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://crackling-heat-5434.firebaseio.com/question/Quiz/subject");

        spSubject = (Spinner) findViewById(R.id.spinner_subject);
        spChapters = (Spinner) findViewById(R.id.spinner_chapter);
        btnNext = (Button) findViewById(R.id.btn_next);

        spSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                chapters.clear();
                spChapters.setAdapter(null);
                refChapter = ref.child(spSubject.getItemAtPosition(position).toString());
                refChapter.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        chapters.add(dataSnapshot.getKey());
                        String[] chaptersArr = new String[chapters.size()];
                        chaptersArr = chapters.toArray(chaptersArr);

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, chaptersArr);
                        spChapters.setAdapter(spinnerArrayAdapter);

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

        spChapters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // clear list first
                AppUtilities.getScoreList().clear();

                refHighScore = refChapter.child(spChapters.getItemAtPosition(position).toString()).child("scorelist");
                refHighScore.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        Score score = new Score();
                        score.setKey(dataSnapshot.getKey());
                        score.setName(dataSnapshot.child("name").getValue().toString());
                        score.setDate(dataSnapshot.child("date").getValue().toString());
                        score.setPoints(Integer.parseInt(dataSnapshot.child("points").getValue().toString()));

                        AppUtilities.getScoreList().add(score);
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


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!AppUtilities.getScoreList().isEmpty()) {
                    Collections.sort(AppUtilities.getScoreList(), new ScoreComparator());
                    Collections.reverse(AppUtilities.getScoreList());
                }

                if (spChapters.getSelectedItem() != null) {
                    AppUtilities.setSubject(spSubject.getSelectedItem().toString());
                    AppUtilities.setChapter(spChapters.getSelectedItem().toString());
                    Intent intent = new Intent(TeacherGradesSelection.this, TeacherGrades.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please make a selection.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
