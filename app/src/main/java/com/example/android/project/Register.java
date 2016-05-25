package com.example.android.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.project.models.User;
import com.firebase.client.Firebase;

public class Register extends AppCompatActivity {
    Firebase firebase;
    TextView registerPass, registerName,registerClass ,registerEmail;
    Button confirm;
    RadioButton teacher, student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firebase.setAndroidContext(this);

    }

    public void confirm(View view) {

        registerPass = (TextView) findViewById(R.id.registerPass);
        registerName = (TextView) findViewById(R.id.registerName);
        registerClass = (TextView) findViewById(R.id.registerClass);
        registerEmail = (TextView) findViewById(R.id.registerEmail);
        confirm = (Button) findViewById(R.id.confirm);
        teacher = (RadioButton) findViewById(R.id.teacher);
        student = (RadioButton) findViewById(R.id.student);

        if (teacher.isChecked()) {
            String username, password,classname,email;
            username = registerName.getText().toString();
            password = registerPass.getText().toString();
            classname=registerClass.getText().toString();
            email =registerEmail.getText().toString();

            User user = new User("TEACHER", username, password,classname,email);
            FirebaseUpdate.newUser(user, FirebaseClass.newUser());
            Toast.makeText(Register.this, "Teacher Created...! ", Toast.LENGTH_SHORT).show();

        } else if (student.isChecked()) {
            String username, password ,classname,email;
            username = registerName.getText().toString();
            password = registerPass.getText().toString();
            classname=registerClass.getText().toString();
            email =registerEmail.getText().toString();

            User user = new User("STUDENT", username, password,classname,email);
            FirebaseUpdate.newUser(user, FirebaseClass.newUser());
            Toast.makeText(Register.this, "Student Created...! ", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}


