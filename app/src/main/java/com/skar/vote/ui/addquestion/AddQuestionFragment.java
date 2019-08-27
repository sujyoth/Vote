package com.skar.vote.ui.addquestion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.skar.vote.R;

public class AddQuestionFragment extends Fragment {

    private AddQuestionViewModel addQuestionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addQuestionViewModel =
                ViewModelProviders.of(this).get(AddQuestionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_question, container, false);

        return root;
    }
}