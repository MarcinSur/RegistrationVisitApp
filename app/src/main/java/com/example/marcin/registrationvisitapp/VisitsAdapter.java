package com.example.marcin.registrationvisitapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marcin.registrationvisitapp.data.Visit;

import java.util.ArrayList;
import java.util.List;

public class VisitsAdapter extends RecyclerView.Adapter<VisitsViewHolder> {

    private List<Visit> mVisits = new ArrayList<>();
    private int pos;

    @NonNull
    @Override
    public VisitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.visit_item_recyclerview, parent, false);

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
