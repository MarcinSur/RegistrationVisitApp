package com.example.marcin.registrationvisitapp;


import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class VisitsViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public VisitsViewHolder(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.name_text);
    }
}
