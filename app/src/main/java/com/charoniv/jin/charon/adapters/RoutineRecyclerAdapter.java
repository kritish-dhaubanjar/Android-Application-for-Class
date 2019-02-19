package com.charoniv.jin.charon.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.charoniv.jin.charon.R;
import com.charoniv.jin.charon.datasource.Class;

import java.util.List;

public class RoutineRecyclerAdapter extends RecyclerView.Adapter<RoutineRecyclerAdapter.RoutineViewHolder> {

    private static final String TAG = "RoutineRecylerAdapter";
    private LayoutInflater layoutInflater;
    private List<Class> items;
    private Context context;

    public RoutineRecyclerAdapter(Context context, List<Class> items) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.routine_item, viewGroup, false);
        return new RoutineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder routineViewHolder, int position) {

        Class routineClass = items.get(position);

        routineViewHolder.start.setText(routineClass.getStart());
        routineViewHolder.end.setText(routineClass.getEnd());
        routineViewHolder.type.setText(routineClass.getType());
        routineViewHolder.subject.setText(routineClass.getSubject());
        routineViewHolder.instructor.setText(routineClass.getInstructor());

        if(routineClass.getSubject().equals("Break"))
            routineViewHolder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorDivider));
        else
            routineViewHolder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
    }

    @Override
    public int getItemCount() {
        return (items!=null && items.size()>0) ? items.size() : 0;
    }

    public void onRequest(List<Class> items){
        this.items = items;
        notifyDataSetChanged();
    }


    static class RoutineViewHolder extends RecyclerView.ViewHolder{

        TextView start, end, subject, type, instructor;
        ConstraintLayout layout;

        RoutineViewHolder(@NonNull View itemView) {
            super(itemView);
            start = itemView.findViewById(R.id.start);
            end = itemView.findViewById(R.id.end);
            type = itemView.findViewById(R.id.type);
            subject = itemView.findViewById(R.id.subject);
            instructor = itemView.findViewById(R.id.instructor);
            layout = itemView.findViewById(R.id.layout);
        }
    }

}
