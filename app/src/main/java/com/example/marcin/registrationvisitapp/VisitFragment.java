package com.example.marcin.registrationvisitapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.marcin.registrationvisitapp.data.Visit;
import com.example.marcin.registrationvisitapp.ui.viewmodels.VisitViewModel;
import com.example.marcin.registrationvisitapp.ui.VisitDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class VisitFragment extends Fragment implements VisitDialogListener {

    private VisitViewModel mViewModel;
    VisitDialog dialog = new VisitDialog();
    private RecyclerView mRecyclerView;
    private VisitsAdapter mVisitsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Visit> mVisits = new ArrayList<>();



    public static VisitFragment newInstance() {

        return new VisitFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.visit_fragment, container, false);

        dialog.setupVisitDialogListeners(this);

        Button button = view.findViewById(R.id.addVisit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show(getFragmentManager(), "VisitDialogFragment");
            }
        });

        mRecyclerView = view.findViewById(R.id.visit_recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mVisitsAdapter = new VisitsAdapter();
        mRecyclerView.setAdapter(mVisitsAdapter);

        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        myRef = database.getReference("visits");
        myRef.keepSynced(true);

        readChildFromDatabase();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(VisitViewModel.class);
        // TODO: Use the ViewModel
    }

    private void readChildFromDatabase(){
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mVisitsAdapter.add(dataSnapshot.getValue(Visit.class));
                Log.w("readChildFromDatabase","onChildAdded");
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
        });
    }

    @Override
    public void onDialogSaveClick(Visit visit) {
        myRef.push().setValue(visit);
        dialog.getDialog().cancel();
        //Toast.makeText(getContext(),"zapisano " + visit.getName(),Toast.LENGTH_SHORT).show();
        Snackbar.make(getView(),"Zapisano",Snackbar.LENGTH_SHORT).show();
    }
}
