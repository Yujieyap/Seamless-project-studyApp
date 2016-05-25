package com.example.android.project;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.project.Comparator.HighScoreComparator;
import com.example.android.project.models.Quiz;
import com.example.android.project.models.QuizQuestion;
import com.example.android.project.models.Score;
import com.example.android.project.utilities.AppUtilities;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Collections;

public class ChaptersGrade extends AppCompatActivity {
    Firebase mRootRef, ref, childdsRef;
    int count2;
    final private ArrayList<String> mMessages = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<Quiz> quizList = new ArrayList<Quiz>();
    private String selectedText = "";
    private String types = "";
    ListView mListView;
    private ArrayList<QuizQuestion> qList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters_grade);
        Firebase.setAndroidContext(this);
        Bundle b = getIntent().getExtras();
        AppUtilities.getHighScore().clear();

        Button logout = (Button) findViewById(R.id.button31);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ChaptersGrade.this, Login.class));
            }
        });


//        for(int i=AppUtilities.getHighScore().size()-1; i>=0; i--){
//            System.out.println(AppUtilities.getHighScore().get(i).getPoints());
//        }
//        System.out.println("POLO2" + AppUtilities.getHighScore());


        if (b != null) {
            selectedText = b.getString("subject");
        }

        final Firebase mRootRef = new Firebase("https://crackling-heat-5434.firebaseio.com");
        final Firebase mRootRefs = new Firebase("https://crackling-heat-5434.firebaseio.com/");

        mListView = (ListView) findViewById(R.id.listView);
        ref = mRootRef.child("question").child("Quiz").child("subject").child(selectedText);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Log.i("Start", "started");
                final Quiz qObj = new Quiz((String) dataSnapshot.getKey());
                quizList.add(qObj);
                System.out.println("TEST : " + qObj.getName());

                mMessages.add(qObj.getName().toString());
//                    adapter.notifyDataSetChanged();


                adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1, mMessages) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        // Get the Item from ListView
                        View view = super.getView(position, convertView, parent);

                        // Initialize a TextView for ListView each Item
                        TextView tv = (TextView) view.findViewById(android.R.id.text1);

                        // Set the text color of TextView (ListView Item)
                        tv.setTextColor(Color.BLUE);

                        // Generate ListView Item using TextView
                        return view;
                    }
                };
                mListView.setAdapter(adapter);


                mListView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                                Firebase childsRef = mRootRefs.child("question").child("Quiz").child("subject").child(selectedText).child(mListView.getItemAtPosition(position).toString()).child("questions");
//                                Firebase refScore = mRootRefs.child("question").child("Quiz").child("subject").child(selectedText).child(mListView.getItemAtPosition(position).toString()).child("scorelist");
//                                refScore.addChildEventListener(new ChildEventListener() {
//                                    @Override
//                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                                        int points = (int) (long) dataSnapshot.child("points").getValue();
//                                        String name = (String) dataSnapshot.child("name").getValue();
//                                        String date = (String) dataSnapshot.child("date").getValue();
//
//
//                                        Score q = new Score(name, points, date);
//                                        AppUtilities.getHighScore().add(q);
//
////                                        Collections.sort(AppUtilities.getScoreLists(), new HighScoreComparator());
////
////                                        Collections.reverse(AppUtilities.getScoreLists());
//
//
//
//                                        for (int i = 0; i < AppUtilities.getScoreLists().size(); i++) {
//                                            System.out.println("DIULEILOH" + AppUtilities.getScoreLists().get(i).getPoints());
//                                            System.out.println("DIULEILOH" + AppUtilities.getScoreLists().get(i).getName());
//                                            System.out.println("DIULEILOH" + AppUtilities.getScoreLists().get(i).getDate());
//                                        }
//                                        for (Score k : AppUtilities.getScoreLists()) {
//                                            System.out.println("Name: " + k.getName() + "\tScore: " + k.getPoints() + "\tDate: " + k.getDate());
//                                        }
////
//
//                                    }
//
//                                    @Override
//                                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                                    }
//
//                                    @Override
//                                    public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                                    }
//
//                                    @Override
//                                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(FirebaseError firebaseError) {
//
//                                    }
//                                });


                                Firebase childdsRef = mRootRefs.child("question").child("Quiz").child("subject").child(selectedText).child(mListView.getItemAtPosition(position).toString()).child("scorelist");
                                Collections.sort(AppUtilities.getScoreLists(), new HighScoreComparator());
                                childdsRef.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                        int points = (int) (long) (dataSnapshot.child("points").getValue());

                                        System.out.println("dsadasdasdsadsa" + points);

                                        String date = (String) dataSnapshot.child("date").getValue();
                                        System.out.println("dsadasdasdsadsa" + date);

                                        String name = (String) dataSnapshot.child("name").getValue();
                                        System.out.println("dsadasdasdsadsa" + name);


                                        Score as = new Score(name, points, date);
                                        System.out.println("POLOGG" + as);

                                        AppUtilities.getHighScore().add(as);


                                        for (int i = 0; i < AppUtilities.getHighScore().size(); i++) {
                                            System.out.println("sdsdsdsdsdsadas" + AppUtilities.getHighScore().get(i).getPoints());
                                            System.out.println("sdsdsdsdsdsadas" + AppUtilities.getHighScore().get(i).getName());
                                            System.out.println("sdsdsdsdsdsadas" + AppUtilities.getHighScore().get(i).getDate());
                                        }


                                        for (Score k : AppUtilities.getHighScore()) {
                                            System.out.println("Name: " + k.getName() + "\tScore: " + k.getPoints() + "\tDate: " + k.getDate());
                                        }

//                                        Collections.sort(AppUtilities.getHighScore(), new HighScoreComparator());

                                        System.out.println("*******************Before reverse*******************");
                                        for (Score k : AppUtilities.getHighScore()) {
                                            System.out.println("Name: " + k.getName() + "\tScore: " + k.getPoints() + "\tDate: " + k.getDate());
                                        }

//                                        Collections.reverse(AppUtilities.getHighScore());

                                        System.out.println();
                                        System.out.println("*******************After reverse*******************");

                                        for (Score k : AppUtilities.getHighScore()) {
                                            System.out.println("Name: " + k.getName() + "\tScore: " + k.getPoints() + "\tDate: " + k.getDate());
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

//                                for (Score k : AppUtilities.getHighScore()) {
//                                    System.out.println("Name: " + k.getName() + "\tScore: " + k.getPoints() + "\tDate: " + k.getDate());
//                                }

                                new CountDownTimer(2000, 1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        mListView.setEnabled(false);
                                        Toast.makeText(getApplication(), "Loading", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFinish() {
                                        mListView.setEnabled(true);
                                        if (AppUtilities.getHighScore
                                                ().size() == 0) {
                                            Intent nextsactivity = new Intent(ChaptersGrade.this, Error.class);
                                            startActivity(nextsactivity);
                                        }
                                        else{
                                            Intent nextactivity = new Intent(ChaptersGrade.this, StudentViewGrades.class);

                                            final Bundle e = new Bundle();
                                            e.putString("chapt", mMessages.get(position));
                                            Bundle b = new Bundle();
                                            b.putString("subject", selectedText);


                                            nextactivity.putExtras(b);

                                            nextactivity.putExtras(e);


                                            startActivity(nextactivity);
                                            Log.d("SIAO", types);
                                            Log.d("BYE", selectedText);
                                        }

                                    }
                                }.start();


                            }
                        });


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

        System.out.println("SDSDSDSDS" + AppUtilities.getHighScore());

//            System.out.print("DIU LEI " + AppUtilities.getScoreLists().get(0));


    }


    @Override
    protected void onResume() {
        super.onResume();
        AppUtilities.getQuestionList().clear();
        AppUtilities.getScoreLists().clear();

    }
}
