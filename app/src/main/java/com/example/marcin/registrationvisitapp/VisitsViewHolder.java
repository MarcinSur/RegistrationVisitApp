package com.example.marcin.registrationvisitapp;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VisitsViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public VisitsViewHolder(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.name_text);
    }
}
