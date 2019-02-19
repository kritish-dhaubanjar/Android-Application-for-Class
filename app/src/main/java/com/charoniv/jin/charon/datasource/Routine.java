package com.charoniv.jin.charon.datasource;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.charoniv.jin.charon.activities.MainActivity;
import com.charoniv.jin.charon.adapters.RoutineRecyclerAdapter;
import com.charoniv.jin.charon.calendar.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Routine implements RoutineDatabase.OnReadyForRefresh{

    private List<Class> sunday, monday, tuesday, wednesday, thursday;
    private Context context;
    private RoutineDatabase routineDatabase;
    private HashMap<String, List<Class>> routine;
    private RoutineRecyclerAdapter recyclerAdapter;

    public Routine(Context context) {
        this.context = context;
        this.routineDatabase = new RoutineDatabase(context, this);
        this.recyclerAdapter = null;
        onReadyForRefresh();
    }

    public void setRecyclerAdapter(RoutineRecyclerAdapter recyclerAdapter) {
        this.recyclerAdapter = recyclerAdapter;
    }

    public void updateDatabase(){
        routineDatabase.updateDatabase();
    }

    public List<Class> getItems(int day) {
        switch (day){
            case 1:
                return sunday;
            case 2:
                return monday;
            case 3:
                return tuesday;
            case 4:
                return wednesday;
            case 5:
                return thursday;
            case 6:
                return null;
            case 7:
                return null;
        }
        return null;
    }

    @Override
    public void onReadyForRefresh() {
        routine = routineDatabase.selectFromDatabase();
        sunday = routine.get("sunday");
        monday = routine.get("monday");
        tuesday = routine.get("tuesday");
        wednesday = routine.get("wednesday");
        thursday = routine.get("thursday");
        if(recyclerAdapter!=null) {
            recyclerAdapter.onRequest(getItems(MainActivity.dayInt));
        }
    }

}
