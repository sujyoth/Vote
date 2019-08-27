package com.skar.vote.ui.addquestion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddQuestionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddQuestionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is addquestion fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}