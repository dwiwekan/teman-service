package com.example.mod5.ui.dailycare;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DailyCareViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DailyCareViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is daily care fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}