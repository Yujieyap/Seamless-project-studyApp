package com.example.android.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.project.models.ChatMessage;
import com.example.android.project.models.User;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import java.util.HashMap;
import java.util.Map;


public class ChatActivity extends AppCompatActivity {
    private Firebase firebase;
    Button btn;
    EditText txt;
    FirebaseListAdapter<ChatMessage> listAdapter;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Firebase.setAndroidContext(this);
        firebase = new Firebase(FirebaseClass.FIREBASE_CHAT);
        txt = (EditText)findViewById(R.id.txt);
        btn = (Button)findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                user = SharedPrefeence.getLoggedInUser(ChatActivity.this);

                String text = txt.getText().toString();
                Map<String,Object> values = new HashMap<>();
                values.put("name", user.getName());
                values.put("text", text);
                firebase.push().setValue(values);
                txt.setText("");

            }
        });


        ListView listView = (ListView) this.findViewById(R.id.listTest);
        listAdapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                android.R.layout.two_line_list_item, firebase) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                ((TextView)v.findViewById(android.R.id.text1)).setText(model.getName());
                ((TextView)v.findViewById(android.R.id.text2)).setText(model.getText());
            }
        };
        listView.setAdapter(listAdapter);

    }
}
