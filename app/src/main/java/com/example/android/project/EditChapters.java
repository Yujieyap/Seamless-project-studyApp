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

import com.example.android.project.models.Quiz;
import com.example.android.project.models.QuizQuestion;
import com.example.android.project.models.Score;
import com.example.android.project.utilities.AppUtilities;
import com.example.android.project.utilities.UserUitilities;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class EditChapters extends AppCompatActivity {
    Firebase mRootRef, ref, refScore, firebaseref, childdsRef, refHigh, ref2;
    int count2;
    final private ArrayList<String> mMessages = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<Quiz> quizList = new ArrayList<Quiz>();
    private String selectedText = "";
    private String types = "";
    private String key = "";

    boolean result = false;
    boolean callback = false;
    String done = "";


    ListView mListView;
    private ArrayList<QuizQuestion> qList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_chapters);

        Firebase.setAndroidContext(this);
        Button logout = (Button) findViewById(R.id.button31);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(EditChapters.this, Login.class));
            }
        });

        Bundle b = getIntent().getExtras();

        if (b != null) {
            selectedText = b.getString("subject");
        }

        final Firebase mRootRef = new Firebase("https://crackling-heat-5434.firebaseio.com/");
        final Firebase mRootRefs = new Firebase("https://crackling-heat-5434.firebaseio.com/");

        mListView = (ListView) findViewById(R.id.listViewEdit);
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
                                refHigh = mRootRef.child("question").child("Quiz").child("subject").child(selectedText).child(mListView.getItemAtPosition(position).toString()).child("scorelist");
                                Firebase childsRef = mRootRefs.child("question").child("Quiz").child("subject").child(selectedText).child(mListView.getItemAtPosition(position).toString()).child("questions");

                                childsRef.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                        AppUtilities.setChapterSelected(mListView.getItemAtPosition(position).toString());
                                        String type = (String) dataSnapshot.child("type").getValue();

                                        String question = (String) dataSnapshot.child("question").getValue();
                                        String answer = (String) dataSnapshot.child("answer").getValue();
                                        ArrayList<String> choices = (ArrayList<String>) dataSnapshot.child("choices").getValue();

                                        QuizQuestion q = new QuizQuestion(choices, type, question, answer, dataSnapshot.getKey());
                                        AppUtilities.getQuestionList().add(q);
                                        System.out.println("HELLLLOOOOOO" + AppUtilities.getQuestionList());


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
                                refHigh.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        int pointss = Integer.parseInt(dataSnapshot.child("points").getValue().toString());
                                        Score q = new Score(pointss);
                                        AppUtilities.setMyScore(q);
                                        System.out.println("ASDFSDAFA" + q + AppUtilities.getMyScore());
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

//

                                new CountDownTimer(2000, 1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        mListView.setEnabled(false);
                                        Toast.makeText(getApplication(), "Loading", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFinish() {

                                        mListView.setEnabled(true);

                                        ref2 = mRootRef.child("question").child("Quiz").child("subject").child(selectedText).child(mListView.getItemAtPosition(position).toString()).child("questions");

                                        ref2.addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                                String key = dataSnapshot.getKey();
                                                QuizQuestion qs = new QuizQuestion(key);
                                                AppUtilities.getKey().add(qs);


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

                                        refScore = mRootRef.child("question").child("Quiz").child("subject").child(selectedText).child(mListView.getItemAtPosition(position).toString()).child("scorelist");
                                        refScore.addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                                                int points = Integer.parseInt(dataSnapshot.child("points").getValue().toString());
                                                String date = dataSnapshot.child("date").getValue().toString();
                                                String name = dataSnapshot.child("name").getValue().toString();
                                                String key = dataSnapshot.getKey();
                                                Score q = new Score(name, points, date, key);
                                                AppUtilities.getScoreLists().add(q);


                                                System.out.println("Name: " + dataSnapshot.child("name").getValue().toString());
//                            name.add(dataSnapshot.child("name").getValue().toString());
                                                callback = false;
                                                if (dataSnapshot.child("name").getValue().equals(UserUitilities.getUsername())) {
                                                    result = true;
                                                    key = dataSnapshot.getKey();


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

                                        if(AppUtilities.getQuestionList().size() == 0) {
                                            System.out.println("FUCK U" + AppUtilities.getQuestionList().size());
                                            Intent nextsactivity = new Intent(EditChapters.this, Error.class);
                                            startActivity(nextsactivity);
                                        }
                                        else{
                                            Intent nextactivity = new Intent(EditChapters.this, EditQuiz.class);
                                            Bundle b = new Bundle();
                                            b.putString("subject", selectedText);


                                            nextactivity.putExtras(b);


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


    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtilities.getQuestionList().clear();
    }
}

