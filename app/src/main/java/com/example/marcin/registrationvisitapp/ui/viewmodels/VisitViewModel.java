package com.example.marcin.registrationvisitapp.ui.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.marcin.registrationvisitapp.Repository.VisitRepository;
import com.example.marcin.registrationvisitapp.data.Visit;

import java.util.List;

public class VisitViewModel extends AndroidViewModel {

    private VisitRepository mRepository;
    private LiveData<List<Visit>> mAllVisits;

    public VisitViewModel(Application application) {
        super(application);
        mRepository = new VisitRepository(application);
        mAllVisits = mRepository.getAllVisits();
    }

    LiveData<List<Visit>> getAllVisits() {return mAllVisits;}
    public void insertVisit(Visit visit){mRepository.insert(visit);}

}
