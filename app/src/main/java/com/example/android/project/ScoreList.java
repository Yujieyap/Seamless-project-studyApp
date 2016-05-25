package com.example.android.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ScoreList extends AppCompatActivity {

    public ScoreList(ScoreList scoreList, ArrayList<HashMap<String, String>> list) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_score_list);
//        final ArrayList<HashMap<String, String>> list = new ArrayList<>();
//        ListView listView=(ListView)findViewById(R.id.list);
////        ArrayAdapter<HashMap<String,String>> adapter;
//        ListView mListView;
//
//
//
//        HashMap<String,String> temp=new HashMap<String, String>();
//        temp.put(FIRST_COLUMN, "Ankit Karia");
//        temp.put(SECOND_COLUMN, "Male");
//
//        list.add(temp);
//
//        HashMap<String,String> temp2 = new HashMap<String, String>();
//        temp2.put(FIRST_COLUMN, "Rajat Ghai");
//        temp2.put(SECOND_COLUMN, "Male");
//
//        list.add(temp2);
//
//        HashMap<String,String> temp3=new HashMap<String, String>();
//        temp3.put(FIRST_COLUMN, "Karina Kaif");
//        temp3.put(SECOND_COLUMN, "Female");
//
//        list.add(temp3);
//
//        Score adapter=new Score(this, list);
//        listView.setAdapter((ListAdapter) adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
//            {
//                int pos=position+1;
//                Toast.makeText(Score.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//    }
    }
}



