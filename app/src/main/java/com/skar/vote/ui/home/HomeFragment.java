package com.skar.vote.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.flowlayout.FlowLayout;
import com.skar.vote.ActivityQuestions;
import com.skar.vote.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FlowLayout topics;
    private Button[] buttonTopic = new Button[120];
    private DatabaseReference databaseReferenceTopics;
    private ArrayList<String> topicList = new ArrayList<>();
    private Integer i = 0;
    private View root;
    
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);

        topics = root.findViewById(R.id.layoutTopics);

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

        return root;
    }

    public void addButton(String buttonName, final String parentName) {
        buttonTopic[i] = new Button(root.getContext());
        buttonTopic[i].setText(buttonName);
        buttonTopic[i].setTag(R.id.TAG_PARENT_NAME, parentName); // adding parent of selected topic as tag
        buttonTopic[i].setTag(R.id.TAG_SELECTED, false);
        buttonTopic[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), ActivityQuestions.class); //Making an intent to open Interests page
                startActivity(intent);
            }
        });
        topics.addView(buttonTopic[i]);
        i++;
    }

    private void collectTopics(DataSnapshot dataSnapshot) {
        String parentName;
        for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
            parentName = dataSnapshot2.getKey();
            for (DataSnapshot dataSnapshot3 : dataSnapshot2.getChildren()) {
                addButton(dataSnapshot3.getKey(), parentName);
            }
        }
    }
}