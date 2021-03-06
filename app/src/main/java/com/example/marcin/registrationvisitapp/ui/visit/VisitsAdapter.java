package com.example.marcin.registrationvisitapp.ui.visit;

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

import com.example.marcin.registrationvisitapp.DetailsActivity;
import com.example.marcin.registrationvisitapp.R;
import com.example.marcin.registrationvisitapp.utilities.converter.DateConverter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VisitsAdapter extends RecyclerView.Adapter<VisitsViewHolder> {

    private List<Visit> mVisits = new ArrayList<>();
    private int pos;
    private Activity activity;

    private Button button;
    private RecyclerView recyclerView;

    public VisitsAdapter(Activity activity) {
        this.activity = activity;

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public VisitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.visit_item_recyclerview, parent, false);

        button = view.findViewById(R.id.time_button);
        button.setOnClickListener(b->{
        });
        view.setOnClickListener(v -> {

            Intent i = new Intent(activity, DetailsActivity.class);
            activity.getWindow().setEnterTransition(new Fade(Fade.IN));
            activity.getWindow().setExitTransition(new Fade(Fade.OUT));

//            ActivityOptions transitionActivityOptions = ActivityOptions.
//                    makeSceneTransitionAnimation(activity,
//                            new Pair<>(checkBox, activity.getString(R.string.blue_name)));

            activity.startActivity(i);
        });
        VisitsViewHolder vh = new VisitsViewHolder(view);
        pos = vh.getAdapterPosition();

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VisitsViewHolder holder, int position) {
        holder.textView.setText(mVisits.get(position).getName());
        if (mVisits.get(position).getDate() != null)
            holder.button.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(mVisits.get(position).getDate()));
        if (mVisits.get(position).getTimeStamp() != 0)
            holder.button.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(DateConverter.toDate(mVisits.get(position).getTimeStamp())));
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
        int endOfList = mVisits.size() - 1;
        Log.w("VisitAdapter", Integer.toString(mVisits.size()));
    }

    public void addAll(List<Visit> list) {
        mVisits.clear();
        mVisits.addAll(list);
        int endOfList = mVisits.size() - 1;
        //recyclerView.smoothScrollToPosition(endOfList);
        Log.w("AddALL", Integer.toString(list.size()));
        //notifyItemRangeInserted(mVisits.size(),list.size());
    }
}
