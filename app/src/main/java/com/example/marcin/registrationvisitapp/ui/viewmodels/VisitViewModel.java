package com.example.marcin.registrationvisitapp.ui.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;

import com.example.marcin.registrationvisitapp.repository.VisitRepository;
import com.example.marcin.registrationvisitapp.data.Visit;


import java.util.ArrayList;
import java.util.List;

public class VisitViewModel extends AndroidViewModel {

    private VisitRepository mRepository;
    private MutableLiveData<List<Visit>> mAllVisits = new MutableLiveData<>();

    //FirebaseDatabase database;
    //DatabaseReference myRef;

    public VisitViewModel(Application application) {
        super(application);
        mRepository = new VisitRepository(application);
        //mAllVisits = mRepository.getAllVisits();


        //database = FirebaseDatabase.getInstance();
        //database.setPersistenceEnabled(true);
        //myRef = database.getReference("visits");
        //myRef.keepSynced(true);

        readChildFromDatabase();
    }

    public LiveData<List<Visit>> getAllVisits() {
        if(mAllVisits.getValue() == null){
            loadAllFromFirebase();
        }
        return mAllVisits;
    }
    public void insertVisit(Visit visit){
        mRepository.insert(visit);
    }

    public void insertVisitToFirebase(Visit visit){
        mAllVisits.getValue().add(visit);
    }

    public void saveToFirebase(Visit visit){

    }

    private LiveData<List<Visit>> loadAllFromFirebase(){
//        final List<Visit> visits = new ArrayList<>();
//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot visitSnapshot: dataSnapshot.getChildren())
//                {
//                    visits.add(visitSnapshot.getValue(Visit.class));
//                }
//                mAllVisits.setValue(visits);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        return mAllVisits;
    }

    private void readChildFromDatabase(){
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Log.w("readChildFromDatabase","onChildAdded");
//                if(mAllVisits.getValue() == null) {
//                    final List<Visit> visits = new ArrayList<>();
//                    mAllVisits.setValue(visits);
//                }
//
//                insertVisitToFirebase(dataSnapshot.getValue(Visit.class));
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

}
