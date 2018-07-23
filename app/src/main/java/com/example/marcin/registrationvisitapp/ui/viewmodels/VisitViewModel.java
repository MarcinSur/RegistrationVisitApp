package com.example.marcin.registrationvisitapp.ui.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.marcin.registrationvisitapp.FirebaseQueryLiveData;
import com.example.marcin.registrationvisitapp.data.Visit;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class VisitViewModel extends AndroidViewModel {

    private MutableLiveData<List<Visit>> mAllVisits = new MutableLiveData<>();

    private static final DatabaseReference FIREBASE_VISITS_REF = FirebaseDatabase.getInstance().getReference("/visits");
    private final FirebaseQueryLiveData firebaseData = new FirebaseQueryLiveData(FIREBASE_VISITS_REF);
    private MutableLiveData<List<Visit>> visitData = new MutableLiveData<>();

    private List<Visit> allVisits = new ArrayList<>();

    public final LiveData<List<Visit>> visits =
            Transformations.switchMap(firebaseData, (visit) -> getVisit(visit));

    public VisitViewModel(Application application) {
        super(application);
        FIREBASE_VISITS_REF.keepSynced(true);
    }

    private void getLastVisit(){
        if(allVisits.contains(firebaseData.getCurrentSnap().getValue(Visit.class)))
            return;

        allVisits.add(firebaseData.getCurrentSnap().getValue(Visit.class));
    }

    public LiveData<List<Visit>> getVisit(Visit visit) {
        allVisits.add(visit);
        visitData.setValue(allVisits);
        return visitData;
    }

    public void saveToFirebase(Visit visit) {
        FIREBASE_VISITS_REF.push().setValue(visit);
    }

}
