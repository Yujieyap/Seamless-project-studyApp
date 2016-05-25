package com.example.android.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.project.models.User;
import com.example.android.project.utilities.AppUtilities;
import com.example.android.project.utilities.UserUitilities;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class Login extends AppCompatActivity {
    Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebase.setAndroidContext(this);
    }

    public void login(View view) {
        final  EditText login, pass;
        login = (EditText) findViewById(R.id.loginName);
        pass = (EditText)findViewById(R.id.loginPass);
//        UserUitilities.setUsername(login.getText().toString());
        System.out.println(UserUitilities.getUsername() + "ABC");
        User user = new User( "", "", "","","");

        firebase = FirebaseClass.newUser().child(login.getText().toString());
//        user.setName(login.getText().toString());
//        System.out.println("SDSDSDS" + user.getName());
        if (firebase != null) {
            firebase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Toast.makeText(Login.this, "1! ", Toast.LENGTH_SHORT).show();
                    User user = new User( "", "", "","","");

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        if (child.getKey().equalsIgnoreCase("0")) {
                            user.setName(child.getValue().toString());
                            System.out.println("SDSDSDS" + user.getName());
                        }
                        if (child.getKey().equalsIgnoreCase("1")) {
                            user.setPass(child.getValue().toString());
                        }
                        if (child.getKey().equalsIgnoreCase("2")) {
                            user.setClassname(child.getValue().toString());
                        }
                        if (child.getKey().equalsIgnoreCase("3")) {
                            user.setEmail(child.getValue().toString());
                        }
                        if (child.getKey().equalsIgnoreCase("4")) {
                            user.setUserType(child.getValue().toString());
                        }
                    }

                    Toast.makeText(Login.this, "2! ", Toast.LENGTH_SHORT).show();
                    if (user.getPass().equals(pass.getText().toString())){
                        if (user.getUserType().contains("STUDENT")){
                            UserUitilities.setUsername(login.getText().toString());
                            System.out.println("FUCKYOU" + UserUitilities.getUsername());

                            Intent intent = new Intent(Login.this, StudentHomed.class);
                            startActivity(intent);
                            Toast.makeText(Login.this, "Student page! ", Toast.LENGTH_SHORT).show();
                        }
                        else if(user.getUserType().contains("TEACHER")){
                            Intent intent = new Intent(Login.this, TeacherHome.class);
                            startActivity(intent);
                            Toast.makeText(Login.this, "Teacher page! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Login.this,"Invalid Username/Password",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Toast.makeText(Login.this, "FAIL", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

//    public void signUp(View view){
//        Intent intent = new Intent(Login.this, Register.class);
//        startActivity(intent);
//    }

}
