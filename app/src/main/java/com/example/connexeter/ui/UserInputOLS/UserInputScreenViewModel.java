package com.example.connexeter.ui.UserInputOLS;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserInputScreenViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public UserInputScreenViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is UserInputScreen fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}
