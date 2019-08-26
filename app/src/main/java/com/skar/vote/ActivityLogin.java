package com.skar.vote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button buttonLogin, buttonRegister;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Check if already logged in
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(ActivityLogin.this, ActivityLanding.class); //Making an intent to open Interests page
            startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        firebaseAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (email.isEmpty()) {
                    etEmail.setError("Enter your email.");
                    etEmail.requestFocus();
                } else if (password.isEmpty()) {
                    etPassword.setError("Enter your password.");
                    etPassword.requestFocus();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(ActivityLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(ActivityLogin.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(ActivityLogin.this, ActivityLanding.class); //Making an intent to open Interests page
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class); //Making an intent to open login page
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void findViews() {
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.login);
        buttonRegister = findViewById(R.id.register);
    }

}
