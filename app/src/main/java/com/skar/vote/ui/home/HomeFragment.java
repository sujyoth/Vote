package com.skar.vote.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.flowlayout.FlowLayout;
import com.skar.vote.R;
import com.skar.vote.ui.questions.QuestionsFragment;

public class HomeFragment extends Fragment {

    private FlowLayout topics;
    private Button buttonYourTopics;
    private Button[] buttonTopic;
    private DatabaseReference databaseReferenceTopics;
    private View root;
    private int i;
    ProgressBar progressBar;
    
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);

        topics = root.findViewById(R.id.layoutTopics);
        buttonYourTopics = root.findViewById(R.id.yourTopics);
        progressBar = root.findViewById(R.id.trendingProgress);
        buttonTopic = new Button[120];

        buttonYourTopics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new QuestionsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

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
                Fragment fragment = new QuestionsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        topics.addView(buttonTopic[i]);
        i++;
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
        //view.gone HERE for loading view
    }
}