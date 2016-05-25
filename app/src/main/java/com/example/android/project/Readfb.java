package com.example.android.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.project.models.User;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class Readfb extends AppCompatActivity {
    static Firebase myFirebaseRef;
    ArrayAdapter<String> valuesAdapter;
    ArrayList<String> displayArray;
    ArrayList<String> keysArray;
    ListView listView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readfb);
        Firebase.setAndroidContext(this);


        listView = (ListView) findViewById(R.id.listView);

        displayArray = new ArrayList<>();
        keysArray = new ArrayList<>();
        valuesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, displayArray);
        listView.setAdapter(valuesAdapter);
        listView.setOnItemClickListener(itemClickListener);


        myFirebaseRef = new Firebase(FirebaseClass.FIREBASE_FB);
        myFirebaseRef.addChildEventListener(childEventListener);

    }

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            String keyAndValue = "\nTitle: " + dataSnapshot.getKey().toString() + "\nComment: " + dataSnapshot.getValue().toString();
            displayArray.add(keyAndValue);
            keysArray.add(dataSnapshot.getKey().toString());
            updateListView();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String changedKey = dataSnapshot.getKey();
            int changedIndex = keysArray.indexOf(changedKey);
            String keyAndValue = " Title: " + dataSnapshot.getKey().toString() + "\nComment: " + dataSnapshot.getValue().toString();
            displayArray.set(changedIndex, keyAndValue);
            updateListView();
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String deletedKey = dataSnapshot.getKey();
            int removedIndex = keysArray.indexOf(deletedKey);
            keysArray.remove(removedIndex);
            displayArray.remove(removedIndex);
            updateListView();
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    };

    private void updateListView() {
        valuesAdapter.notifyDataSetChanged();
        listView.invalidate();
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String clickedKey = keysArray.get(position);
            myFirebaseRef.child(clickedKey).removeValue();
        }


    };
}
