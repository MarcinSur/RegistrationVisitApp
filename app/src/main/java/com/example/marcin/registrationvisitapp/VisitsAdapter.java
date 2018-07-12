package com.example.marcin.registrationvisitapp;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.transition.Fade;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.marcin.registrationvisitapp.data.Visit;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VisitsAdapter extends RecyclerView.Adapter<VisitsViewHolder> {

    private List<Visit> mVisits = new ArrayList<>();
    private int pos;
    private Activity activity;

    private Button button;
    private CheckBox checkBox;
    private RecyclerView recyclerView;

    public VisitsAdapter(Activity activity) {
        this.activity = activity;

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public VisitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.visit_item_recyclerview, parent, false);

        checkBox = view.findViewById(R.id.checkBox);
        button = view.findViewById(R.id.time_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(activity, DetailsActivity.class);
                activity.getWindow().setEnterTransition(new Fade(Fade.IN));
                activity.getWindow().setExitTransition(new Fade(Fade.OUT));

                ActivityOptions transitionActivityOptions = ActivityOptions.
                        makeSceneTransitionAnimation(activity,
                                new Pair<View, String>(view.findViewById(R.id.checkBox), activity.getString(R.string.blue_name)));

                activity.startActivity(i, transitionActivityOptions.toBundle());
            }
        });
        VisitsViewHolder vh = new VisitsViewHolder(view);
        pos = vh.getAdapterPosition();

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VisitsViewHolder holder, int position) {
        holder.textView.setText(mVisits.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (mVisits == null)
            return 0;
        else
            return mVisits.size();
    }

    public void add(Visit v) {
        mVisits.add(v);
        //notifyItemInserted(0);
        notifyItemChanged(mVisits.size());
        recyclerView.smoothScrollToPosition(mVisits.size());
        Log.w("VisitAdapter", "add");
    }

    public void addAll(List<Visit> list) {
        mVisits = list;
        Log.w("AddALL", Integer.toString(list.size()));
        notifyDataSetChanged();
    }
}
