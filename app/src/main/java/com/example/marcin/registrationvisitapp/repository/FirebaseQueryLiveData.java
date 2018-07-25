package com.example.marcin.registrationvisitapp.repository;

import android.util.Log;

import com.example.marcin.registrationvisitapp.data.Visit;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class FirebaseQueryLiveData extends LiveData<Visit>{

    private final Query query;
    private final FirebaseChildEventListener firebaseChildEventListener = new FirebaseChildEventListener();
    private final List<Visit> dataSnapshots = new ArrayList<>();
    private  DataSnapshot currentSnap;

    public FirebaseQueryLiveData(Query query) {
        this.query = query;
    }

    public FirebaseQueryLiveData(DatabaseReference reference){
        this.query = reference.limitToLast(10);
        query.addChildEventListener(firebaseChildEventListener);
        Log.w("FirebaseQueryLiveData","FirebaseQueryLiveData");
    }

    public List<Visit> getDataSnapshots() {
        return dataSnapshots;
    }

    public DataSnapshot getCurrentSnap(){
        return currentSnap;
    }

    private class FirebaseChildEventListener implements ChildEventListener {

        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            setValue(dataSnapshot.getValue(Visit.class));
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
