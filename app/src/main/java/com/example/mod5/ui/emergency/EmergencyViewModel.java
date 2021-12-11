package com.example.mod5.ui.emergency;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mod5.MainDashboard;

public class EmergencyViewModel extends ViewModel {

    private MutableLiveData<String> mText;
//    private String name = new String;


    public EmergencyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("User Ganteng");
    }

//    public EmergencyViewModel() {
//        name = ("User Ganteng");
//    }

    public LiveData<String> getText() {
        return mText;
    }
}