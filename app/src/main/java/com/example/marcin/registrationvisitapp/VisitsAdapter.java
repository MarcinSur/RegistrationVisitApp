package com.example.marcin.registrationvisitapp;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import com.example.marcin.registrationvisitapp.data.Visit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VisitsAdapter extends RecyclerView.Adapter<VisitsViewHolder> {

    private List<Visit> mVisits = new ArrayList<>();
    private int pos;
    private ConstraintSet constraintSetOld = new ConstraintSet();
    private ConstraintSet constraintSetNew = new ConstraintSet();
    ConstraintLayout constraintLayout;
    private Button button;

    @NonNull
    @Override
    public VisitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.visit_item_recyclerview, parent, false);

        constraintLayout = view.findViewById(R.id.constraint_layout);
        constraintSetNew.clone(parent.getContext(), R.layout.visit_item_recyclerview_big);
        constraintSetOld.clone(constraintLayout);

        button = view.findViewById(R.id.time_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transition changeBounds = new ChangeBounds();
                changeBounds.setInterpolator(new OvershootInterpolator());

                TransitionManager.beginDelayedTransition(constraintLayout,changeBounds);
                constraintSetOld.applyTo(constraintLayout);
                Log.w("VisitsViewHolder","onClick");
            }
        });

        VisitsViewHolder vh = new VisitsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VisitsViewHolder holder, int position) {
        holder.textView.setText(mVisits.get(position).getName());

        pos = position;
    }

    @Override
    public int getItemCount() {
        if (mVisits == null)
            return 0;
        else
            return mVisits.size();
    }

    public void add(Visit v){
        mVisits.add(v);
        //notifyItemInserted(0);
        notifyDataSetChanged();
    }

    public void addAll(List<Visit> list) {
        mVisits = list;
        Log.w("AddALL", Integer.toString(list.size()));
        notifyDataSetChanged();
    }
}
