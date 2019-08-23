package com.skar.vote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skar.vote.User.UserDetails;

import java.util.Objects;

public class ActivityRegister extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText etName, etEmail, etPassword;
    Button buttonLogin, buttonRegister;
    RadioGroup radioGender;
    DatabaseReference mDatabase;
    String uID;
    UserDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        findViews();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class); //Making an intent to open login page
                startActivity(intent);
                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                final String name = etName.getText().toString();
                final String gender = ((RadioButton) findViewById(radioGender.getCheckedRadioButtonId())).getText().toString();

                if (name.isEmpty()) {
                    etName.setError("Enter your name.");
                    etName.requestFocus();
                } else if (email.isEmpty()) {
                    etEmail.setError("Enter your email.");
                    etEmail.requestFocus();
                } else if (password.isEmpty()) {
                    etPassword.setError("Enter a password.");
                    etPassword.requestFocus();
                } else if (password.length() < 6) {
                    etPassword.setError("Password should be at least 6 characters long.");
                    etPassword.requestFocus();
                } else if (gender.isEmpty()) {
                    Toast.makeText(ActivityRegister.this, "Choose your gender", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(ActivityRegister.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(ActivityRegister.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                uID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid(); // get Unique ID of user
                                userDetails = new UserDetails(name, gender); // create class User Details
                                mDatabase = FirebaseDatabase.getInstance().getReference(); // Create database reference
                                mDatabase.child("Users").child(uID).setValue(userDetails); // Update user details to database
                                Toast.makeText(ActivityRegister.this, "Start new activity", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class); //Making an intent to open login page
                                //startActivity(intent);
                                //finish();
                            }
                        }
                    });
                }
            }
        });
    }


    private void findViews() {
        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.login);
        buttonRegister = findViewById(R.id.register);
        radioGender = findViewById(R.id.gender);
    }
}
