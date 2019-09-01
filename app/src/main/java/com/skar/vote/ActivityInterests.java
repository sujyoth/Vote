package com.skar.vote;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;

public class ActivityInterests extends AppCompatActivity {
    Button[] buttonTopic = new Button[120];
    Button buttonContinue;
    ProgressBar progressBar;
    FlowLayout flowLayout;
    DatabaseReference databaseReferenceUsers, databaseReferenceTopics;
    ArrayList<String> interestList = new ArrayList<>();
    Integer i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        findViews();

        databaseReferenceTopics = FirebaseDatabase.getInstance().getReference().child("Topics");
        databaseReferenceTopics.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectTopics(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReferenceUsers = FirebaseDatabase.getInstance().getReference().child("Users");

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                for (String interest : interestList) {
                    databaseReferenceUsers.child(uID).child("Interested Topics").child(interest).setValue(1); // write all selected interests to user details
                }
                Intent intent = new Intent(ActivityInterests.this, ActivityLanding.class); //Making an intent to open Interests page
                startActivity(intent);
                finish();
            }
        });

    }

    public void addButton(String buttonName, final String parentName) {
        buttonTopic[i] = new Button(this);
        buttonTopic[i].setText(buttonName);
        buttonTopic[i].setTag(R.id.TAG_PARENT_NAME, parentName); // adding parent of selected topic as tag
        buttonTopic[i].setTag(R.id.TAG_SELECTED, false);

        buttonTopic[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!((Boolean) (v.getTag(R.id.TAG_SELECTED)))) {
                    v.setTag(R.id.TAG_SELECTED, true);
                    ((Button) v).setTextColor(Color.WHITE);
                    v.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                    interestList.add(((Button) v).getText().toString());
                } else {
                    v.setTag(R.id.TAG_SELECTED, false);
                    ((Button) v).setTextColor(Color.BLACK);
                    v.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                    interestList.remove(((Button) v).getText().toString());
                }
            }
        });
        flowLayout.addView(buttonTopic[i]);
        i++;

    }

    public void findViews() {
        flowLayout = findViewById(R.id.layoutTopics);
        buttonContinue = findViewById(R.id.buttonContinue);
        progressBar = findViewById(R.id.interestProgress);

    }

    private void collectTopics(DataSnapshot dataSnapshot) {
        String parentName;
        i = 0;
        for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
            parentName = dataSnapshot2.getKey();
            for (DataSnapshot dataSnapshot3 : dataSnapshot2.getChildren()) {
                addButton(dataSnapshot3.getKey(), parentName);
            }
        }

        progressBar.setVisibility(View.GONE );


    }
}
