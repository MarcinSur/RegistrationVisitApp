package com.example.marcin.registrationvisitapp.ui.visit;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.marcin.registrationvisitapp.repository.FirebaseQueryLiveData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class VisitViewModel extends AndroidViewModel {

    private static final DatabaseReference FIREBASE_VISITS_REF = FirebaseDatabase.getInstance().getReference("/visits");
    private final FirebaseQueryLiveData firebaseData = new FirebaseQueryLiveData(FIREBASE_VISITS_REF);

    public final LiveData<List<Visit>> visit = firebaseData;

    public final MediatorLiveData<List<Visit>> visits = new MediatorLiveData<>();
    public final MutableLiveData<List<Visit>> listMutableLiveData = new MutableLiveData<>();
    private List<Visit> visitList = new ArrayList<>();

    public VisitViewModel(Application application) {
        super(application);
        FIREBASE_VISITS_REF.keepSynced(true);
        visits.addSource(visit, visit -> {
            //visitList.add(visit);
            listMutableLiveData.setValue(visitList);
        });
    }

    public void saveToFirebase(Visit visit) {
        String key = FIREBASE_VISITS_REF.push().getKey();
        visit.setPushKey(key);
        FIREBASE_VISITS_REF.child(key).setValue(visit);
    }
}
