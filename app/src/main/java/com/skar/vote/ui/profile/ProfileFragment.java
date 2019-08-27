package com.skar.vote.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.skar.vote.ActivityLogin;
import com.skar.vote.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    View root;
    Button buttonLogout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        root = inflater.inflate(R.layout.fragment_profile, container, false);
        buttonLogout = root.findViewById(R.id.logout_button);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(root.getContext(), ActivityLogin.class); //Making an intent to open Interests page
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }
}