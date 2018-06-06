package com.example.marcin.registrationvisitapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Button;

import com.example.marcin.registrationvisitapp.data.Visit;
import com.example.marcin.registrationvisitapp.ui.viewmodels.VisitViewModel;
import com.example.marcin.registrationvisitapp.utilities.VisitDialog;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VisitFragment extends Fragment {

    private VisitViewModel mViewModel;
    DialogFragment dialog = new VisitDialog();
    private Button saveBtn;
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

        Button button = view.findViewById(R.id.addVisit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                BottomSheetDialog dialog = new BottomSheetDialog(getContext()); dialog.setContentView(R.layout.visit_dialog);
//                dialog.show();


                dialog.show(getFragmentManager(), "NoticeDialogFragment");

            }
        });

        return view;

        View view = inflater.inflate(R.layout.visit_fragment, container, false);
        saveBtn = view.findViewById(R.id.button2);
        mRecyclerView = view.findViewById(R.id.visit_recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mVisitsAdapter = new VisitsAdapter();
        mRecyclerView.setAdapter(mVisitsAdapter);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("visits");
        myRef.keepSynced(true);

        readChildFromDatabse();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetoFirebaseTestData();
            }
        });
        mViewModel = ViewModelProviders.of(this).get(VisitViewModel.class);
        // TODO: Use the ViewModel
    }

    private void readChildFromDatabse(){
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mVisitsAdapter.add(dataSnapshot.getValue(Visit.class));
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

    private void readFromFirebase() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                mVisits.clear();
                for (DataSnapshot visitSnap : dataSnapshot.getChildren()) {
                    Visit v = visitSnap.getValue(Visit.class);
                    mVisits.add(v);
                }
                mVisitsAdapter.addAll(mVisits);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

    }

    private void savetoFirebaseTestData() {
        Visit visit = new Visit(0, "ble", "boo");
        Log.w("savetoFirebaseTestData", "SAVED");
        myRef.push().setValue(visit);
    }

}
