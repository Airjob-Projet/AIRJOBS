package com.airjob.airjobs.ui.manageProfil;

import android.app.ActionBar;
import android.view.View;

import androidx.appcompat.widget.ActionBarContextView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfilViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfilViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Profil management fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
