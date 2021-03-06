package com.example.marcin.registrationvisitapp.ui.visit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.marcin.registrationvisitapp.R;
import com.example.marcin.registrationvisitapp.utilities.converter.DateConverter;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class VisitDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private IVisitDialogListener saveIVisitDialogListener;
    private TextView mEditText;
    private Calendar calendar;
    private Date date;
    private TextView dateView;

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

        calendar = Calendar.getInstance();
        DatePickerDialog dataPickerDialog = new DatePickerDialog(getActivity(),this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        mEditText = view.findViewById(R.id.username);
        mEditText.requestFocus();
        Button button = view.findViewById(R.id.save_visit);
        button.setOnClickListener(v -> saveVisit());
        Button dateePicker = view.findViewById(R.id.datePicker);
        dateePicker.setOnClickListener(v ->{
            dataPickerDialog.show();
        });
        dateView = view.findViewById(R.id.dateView);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);

        return builder.create();
    }

    public void setupVisitDialogListeners(IVisitDialogListener IVisitDialogListener) {
        saveIVisitDialogListener = IVisitDialogListener;

    }

    private void saveVisit() {
        Log.w("onCreateDialog", "SAVE");
        Visit visit = new Visit(1, mEditText.getText().toString(), "detail", DateConverter.tiTimeStamp(date));
        saveIVisitDialogListener.onDialogSaveClick(visit);
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

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();

        dateView.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(date));
    }
}
