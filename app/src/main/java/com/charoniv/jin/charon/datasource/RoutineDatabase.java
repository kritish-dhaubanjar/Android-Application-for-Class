package com.charoniv.jin.charon.datasource;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.preference.PreferenceManager;
import com.charoniv.jin.charon.DownloadStatus;
import com.charoniv.jin.charon.ParseJSONRoutine;
import com.charoniv.jin.charon.activities.SettingsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class RoutineDatabase implements ParseJSONRoutine.OnParseJSONRoutineComplete{

    private SQLiteDatabase database;
    private Context context;
    private HashMap<String, List<Class>> routine;
    private static final String TAG = "RoutineDatabase";
    private String [] days = {"sunday","monday","tuesday","wednesday","thursday"};
    private OnReadyForRefresh onReadyForRefresh;

    interface OnReadyForRefresh{
        void onReadyForRefresh();
    }

    RoutineDatabase(Context context, OnReadyForRefresh onReadyForRefresh){
        this.context = context;
        this.database = context.openOrCreateDatabase("routine_database.db", Context.MODE_PRIVATE, null);
        this.routine = new HashMap<>();
        this.onReadyForRefresh = onReadyForRefresh;
        database.execSQL("CREATE TABLE IF NOT EXISTS 'sunday' (start VARCHAR(10), stop VARCHAR(10), type VARCHAR(10), subject TEXT, instructor TEXT)");
        database.execSQL("CREATE TABLE IF NOT EXISTS 'monday' (start VARCHAR(10), stop VARCHAR(10), type VARCHAR(10), subject TEXT, instructor TEXT)");
        database.execSQL("CREATE TABLE IF NOT EXISTS 'tuesday' (start VARCHAR(10), stop VARCHAR(10), type VARCHAR(10), subject TEXT, instructor TEXT)");
        database.execSQL("CREATE TABLE IF NOT EXISTS 'wednesday' (start VARCHAR(10), stop VARCHAR(10), type VARCHAR(10), subject TEXT, instructor TEXT)");
        database.execSQL("CREATE TABLE IF NOT EXISTS 'thursday' (start VARCHAR(10), stop VARCHAR(10), type VARCHAR(10), subject TEXT, instructor TEXT)");
    }

    HashMap<String, List<Class>> selectFromDatabase(){
        Cursor cursor = null;
        for (String today : days ) {
            cursor = database.rawQuery("SELECT * FROM " + today, null);
            if (cursor.moveToFirst()) {
                List<Class> classes = new ArrayList<>();
                do {
                    classes.add(new Class(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    ));
                } while (cursor.moveToNext());
                routine.put(today, classes);
            }
        }
        cursor.close();
        return routine;
    }

    void updateDatabase(){
        ParseJSONRoutine parseJSONRoutine = new ParseJSONRoutine(this);
        StringBuilder api = new StringBuilder();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        api.append("http://").append(preferences.getString(SettingsActivity.API, "")).append("/api/routine.php");
        parseJSONRoutine.execute(api.toString());
    }

    @Override
    public void onParseJSONRoutineComplete(HashMap<String, List<Class>> routine, DownloadStatus downloadStatus) {
        if(downloadStatus == DownloadStatus.OK){
            for(String today: days ) {
                SQLiteStatement sql = database.compileStatement("DELETE FROM " + today);
                sql.execute();
                sql = database.compileStatement("INSERT INTO " + today + " VALUES(?,?,?,?,?)");
                for (Class c : routine.get(today)) {
                    sql.bindString(1, c.getStart());
                    sql.bindString(2, c.getEnd());
                    sql.bindString(3, c.getType());
                    sql.bindString(4, c.getSubject());
                    sql.bindString(5, c.getInstructor());
                    sql.execute();
                }
            }
        }
        onReadyForRefresh.onReadyForRefresh();

    }
}
