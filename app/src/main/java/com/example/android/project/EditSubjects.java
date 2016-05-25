package com.example.android.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class EditSubjects extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subjects);




        final String[] subjects = {"Math", "English", "Chinese", "Science"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subjects);
        final ListView tryList = (ListView) findViewById(R.id.list_view);
        tryList.setAdapter(adapter);

        Button logout = (Button) findViewById(R.id.button31);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(EditSubjects.this, Login.class));
            }
        });

        tryList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent nextactivity = new Intent(EditSubjects.this, EditChapters.class);
                        Bundle b = new Bundle();
                        b.putString("subject", subjects[position]);
                        nextactivity.putExtras(b);
                        startActivity(nextactivity);

                    }
                }
        );
    }
}
