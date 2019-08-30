package com.skar.vote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    EditText etFirstName,etLastName, etEmail, etPassword;
    Button buttonLogin, buttonRegister;
    RadioGroup radioGender;
    DatabaseReference mDatabase;
    String uID;
    UserDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
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
                final String firstName = etFirstName.getText().toString();
                final String lastName = etLastName.getText().toString();
                final String gender = ((RadioButton) findViewById(radioGender.getCheckedRadioButtonId())).getText().toString();

                if (firstName.isEmpty()) {
                    etFirstName.setError("Enter your First Name.");
                    etFirstName.requestFocus();
                }else if (lastName.isEmpty()){
                    etLastName.setError("Enter your Last Name.");
                    etLastName.requestFocus();

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
                                userDetails = new UserDetails(firstName,lastName, gender); // create class User Details
                                mDatabase = FirebaseDatabase.getInstance().getReference(); // Create database reference
                                mDatabase.child("Users").child(uID).setValue(userDetails); // Update user details to database

                                Intent intent = new Intent(ActivityRegister.this, ActivityInterests.class); //Making an intent to open Interests page
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }


    private void findViews() {
        etFirstName = findViewById(R.id.firstName);
        etLastName = findViewById(R.id.lastName);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.login);
        buttonRegister = findViewById(R.id.register);
        radioGender = findViewById(R.id.gender);
    }
}
