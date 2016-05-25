package com.example.android.project;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.project.models.User;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class WriteFeedback extends AppCompatActivity {
    Button save;
    static Firebase myFirebaseRef;
    EditText nameEditText;
    EditText messageEditText;
    ProgressBar progressBar;
    ArrayAdapter<String> valuesAdapter;
    ArrayList<String> displayArray;
    ArrayList<String> keysArray;
    ListView listView;
    User user;
    private TextView usernamefb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_feedback);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase(FirebaseClass.FIREBASE_FB);
        save = (Button)findViewById(R.id.save);
        nameEditText = (EditText)findViewById(R.id.name);
        messageEditText= (EditText)findViewById(R.id.message);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        listView = (ListView)findViewById(R.id.listView);
        usernamefb= (TextView)findViewById(R.id.userNamefb) ;

        displayArray  = new ArrayList<>();
        keysArray = new ArrayList<>();
        valuesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,displayArray);

        user = SharedPrefeence.getLoggedInUser(WriteFeedback.this);

        usernamefb.setText(user.getName());
    }

    private void showProgressBar(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void onClick(View v) {
        showProgressBar();
        switch (v.getId()){
            case R.id.save:
                String fbname = user.getName();
                String nameString = nameEditText.getText().toString();
                String messageString = messageEditText.getText().toString();
                save(nameString,messageString,fbname);

                break;

        }
    }

    private void save(String name,String message,String fbname){
        myFirebaseRef.child(name).setValue(message,fbname, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                usernamefb.setText(user.getName());
                nameEditText.setText("");
                messageEditText.setText("");
                hideProgressBar();
            }
        });

    }

}
