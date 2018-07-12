package com.example.marcin.registrationvisitapp.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.marcin.registrationvisitapp.R;
import com.example.marcin.registrationvisitapp.VisitDialogListener;
import com.example.marcin.registrationvisitapp.data.Visit;

public class VisitDialog extends DialogFragment {

    VisitDialogListener saveVisitDialogListener;
    TextView mEditText;

    public VisitDialog() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        positionDialogOnBottom();
        return inflater.inflate(R.layout.visit_dialog, container);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.visit_dialog, null);
        mEditText = view.findViewById(R.id.username);
        mEditText.requestFocus();
        Button button = view.findViewById(R.id.save_visit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveVisit();
            }
        });
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);

        return builder.create();
    }

    public void setupVisitDialogListeners(VisitDialogListener visitDialogListener) {
        saveVisitDialogListener = visitDialogListener;

    }

    private void saveVisit() {
        Log.w("onCreateDialog", "SAVE");
        Visit visit = new Visit(1, mEditText.getText().toString(), "detail");
        saveVisitDialogListener.onDialogSaveClick(visit);
    }

    private void positionDialogOnBottom() {
        this.getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        Window window = getDialog().getWindow();
        // set gravity
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER);
        // then set the values to where you want to position it
        WindowManager.LayoutParams params = window.getAttributes();
        window.setAttributes(params);
    }
}
