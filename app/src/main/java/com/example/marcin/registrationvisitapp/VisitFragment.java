package com.example.marcin.registrationvisitapp;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.marcin.registrationvisitapp.data.Visit;
import com.example.marcin.registrationvisitapp.ui.viewmodels.VisitViewModel;
import com.example.marcin.registrationvisitapp.ui.VisitDialog;
import java.util.ArrayList;
import java.util.List;

public class VisitFragment extends Fragment implements VisitDialogListener {

    private VisitViewModel mViewModel;
    VisitDialog dialog = new VisitDialog();
    private RecyclerView mRecyclerView;
    private VisitsAdapter mVisitsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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

        mVisitsAdapter = new VisitsAdapter(getActivity());
        mRecyclerView.setAdapter(mVisitsAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(VisitViewModel.class);
        mViewModel.getAllVisits().observe(this, visits -> {
            mVisitsAdapter.addAll(visits);
            mRecyclerView.scheduleLayoutAnimation();

        });
    }


    @Override
    public void onDialogSaveClick(Visit visit) {
        mViewModel.saveToFirebase(visit);
        dialog.getDialog().cancel();
        //Toast.makeText(getContext(),"zapisano " + visit.getName(),Toast.LENGTH_SHORT).show();
        Snackbar.make(getView(),"Zapisano",Snackbar.LENGTH_SHORT).show();
    }
}
