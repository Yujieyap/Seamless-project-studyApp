package com.example.android.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.android.project.Comparator.HighScoreComparator;
import com.example.android.project.models.Score;
import com.example.android.project.utilities.AppUtilities;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StudentViewGrades extends AppCompatActivity {
    ArrayAdapter<String> as;
    ListView lv;
    public static final String FIREBASE_URL = "https://crackling-heat-5434.firebaseio.com/question";
    public Firebase firebaseref, refScore;
    private String selectedText = "";
    private String hello = "";

    private ArrayList<HashMap<String, String>> scoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_grades);

        Firebase.setAndroidContext(this);
        Button logout = (Button) findViewById(R.id.button31);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StudentViewGrades.this, Login.class));
            }
        });


        Bundle e = getIntent().getExtras();

        if (e != null) {
            hello = e.getString("chapt");
        }

        Bundle b = getIntent().getExtras();

        if (b != null) {
            selectedText = b.getString("subject");
        }
        firebaseref = new Firebase(FIREBASE_URL);

        lv = (ListView) findViewById(R.id.listView1);

//        Collections.sort(AppUtilities.getHighScore(), new HighScoreComparator());
//        Collections.reverse(AppUtilities.getHighScore());
//        for(int i=0; i<AppUtilities.getHighScore().size(); i++) {
//            HashMap<String, String> map = new HashMap<String, String>();
//
//            map.put("name",  AppUtilities.getHighScore().get(i).getName());
//            map.put("points", String.valueOf(AppUtilities.getHighScore().get(i).getPoints()));
//            scoreList.add(map);
//        }
//
//        SimpleAdapter simpleAdapter = new SimpleAdapter(StudentViewGrades.this, scoreList, R.layout.view_item, new String[]{"name", "points"}, new int[]{R.id.textViewDate, R.id.textViewDescription});
//        lv.setAdapter(simpleAdapter);
        refScore = firebaseref.child("Quiz").child("subject").child(selectedText).child(hello).child("scorelist");


//        Query queryref = refScore.orderByValue();
//
//        queryref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                dataSnapshot.child("points").getValue();
////                Score facts = dataSnapshot.getValue(Score.class);
////                System.out.println(dataSnapshot.getKey() + " was " + facts.getName() + " meters tall");
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });

        for(Score s : AppUtilities.getHighScore()){

            System.out.println("Before - Name: " + s.getName() + "\tPoints: " + s.getPoints() + "\tDate: " + s.getDate());
        }
        Collections.sort(AppUtilities.getHighScore(), new HighScoreComparator());
        Collections.reverse(AppUtilities.getHighScore());
        for(Score s : AppUtilities.getHighScore()){

            System.out.println("After - Name: " + s.getName() + "\tPoints: " + s.getPoints() + "\tDate: " + s.getDate());
        }

        refScore.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                scoreList.clear();

                ArrayList<Score> sList = AppUtilities.getHighScore();
//                sList.clear();
//                Collections.sort(sList, new HighScoreComparator());
//                Collections.reverse(sList);
                for (int i = 0; i < sList.size(); i++) {

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("name", sList.get(i).getName());
                    map.put("points", String.valueOf(sList.get(i).getPoints()));

                    scoreList.add(map);

                }
                sList.clear();

                SimpleAdapter simpleAdapter = new SimpleAdapter(StudentViewGrades.this, scoreList, R.layout.view_item, new String[]{"name", "points"}, new int[]{R.id.textViewDate, R.id.textViewDescription});
                lv.setAdapter(simpleAdapter);


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
        scoreList.clear();


//        refScore.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//
//                for(int i=0; i<AppUtilities.getHighScore().size(); i++) {
//
//
//                    System.out.println("ADSADA " + AppUtilities.getHighScore().get(i).getPoints());
//                    System.out.println("ADSADA " + AppUtilities.getHighScore().get(i).getName());
//                    System.out.println("ADSADA " + AppUtilities.getHighScore().get(i).getDate());
//
////                    Collections.sort(AppUtilities.getHighScore(), new HighScoreComparator());
//
//
//
//
//
//                        SimpleAdapter simpleAdapter = new SimpleAdapter(StudentViewGrades.this, scoreList, R.layout.view_item, new String[]{"name", "points"}, new int[]{R.id.textViewDate, R.id.textViewDescription});
//                    lv.setAdapter(simpleAdapter);
//
//
//
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//
//        });
//

    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    protected void onResume() {
        super.onResume();
//        finalMap.clear();
        scoreList.clear();

        AppUtilities.getScoreLists().clear();
//        sList.clear();


    }
}
