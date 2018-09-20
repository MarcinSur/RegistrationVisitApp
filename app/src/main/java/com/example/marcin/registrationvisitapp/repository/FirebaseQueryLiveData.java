package com.example.marcin.registrationvisitapp.repository;

import android.util.Log;

import com.example.marcin.registrationvisitapp.ui.visit.Visit;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class FirebaseQueryLiveData extends LiveData<List<Visit>>{

    private final Query query;
    private final ValueEventListener firebaseChildEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            List<Visit> visits = new ArrayList<>();
            for (DataSnapshot visitSnapshot: dataSnapshot.getChildren()){
                visits.add(visitSnapshot.getValue(Visit.class));
            }
            setValue(visits);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public FirebaseQueryLiveData(Query query) {
        this.query = query;
    }
    public FirebaseQueryLiveData(DatabaseReference reference){
        this.query = reference.limitToLast(10);
        query.addValueEventListener(firebaseChildEventListener);
        Log.w("FirebaseQueryLiveData","FirebaseQueryLiveData");
    }

    private class FirebaseChildEventListener implements ChildEventListener {

        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Log.w("FirebaseEventListener","onChildAdded");
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
