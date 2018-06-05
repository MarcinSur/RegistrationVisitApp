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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.marcin.registrationvisitapp.ui.viewmodels.VisitViewModel;
import com.example.marcin.registrationvisitapp.utilities.VisitDialog;

public class VisitFragment extends Fragment {

    private VisitViewModel mViewModel;
    DialogFragment dialog = new VisitDialog();

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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(VisitViewModel.class);
        // TODO: Use the ViewModel

    }

}
