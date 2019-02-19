package com.charoniv.jin.charon;

import android.os.AsyncTask;
import android.util.Log;

import com.charoniv.jin.charon.datasource.Class;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParseJSONRoutine extends AsyncTask<String, Void, HashMap<String, List<Class>>> implements GetJSONData.OnGetJSONDataConplete{

    private HashMap<String, List<Class>> routine;
    private OnParseJSONRoutineComplete onParseJSONRoutineComplete;
    private DownloadStatus downloadStatus;
    private static final String TAG = "ParseJSONRoutine";

    public ParseJSONRoutine(OnParseJSONRoutineComplete onParseJSONRoutineComplete){
        this.onParseJSONRoutineComplete = onParseJSONRoutineComplete;
        routine = new HashMap<>();
    }

    public interface OnParseJSONRoutineComplete{
        void onParseJSONRoutineComplete(HashMap<String, List<Class>> routine, DownloadStatus downloadStatus);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(HashMap<String, List<Class>> s) {
        super.onPostExecute(s);
        onParseJSONRoutineComplete.onParseJSONRoutineComplete(s, downloadStatus);
    }

    @Override
    protected HashMap<String, List<Class>> doInBackground(String... strings) {
        GetJSONData getJSONData = new GetJSONData(this);
        getJSONData.run(strings[0]);
        return routine;
    }

    @Override
    public void onGetJSONDataComplete(String json, DownloadStatus downloadStatus) {
        this.downloadStatus = downloadStatus;
        if(downloadStatus == DownloadStatus.OK) {
            try {
                JSONObject object = new JSONObject(json);
                mapClasses(object, "sunday");
                mapClasses(object, "monday");
                mapClasses(object, "tuesday");
                mapClasses(object, "wednesday");
                mapClasses(object, "thursday");
            } catch (JSONException e) {
                Log.e(TAG, "onGetJSONDataComplete: " + e.getMessage() );
            }
        }
    }

    private void mapClasses(JSONObject object, String today){
        try {
            JSONArray day = object.getJSONArray(today);
            List<Class> classes = new ArrayList<>();

            for (int i = 0; i < day.length(); i++) {
                JSONObject temp = day.getJSONObject(i);
                classes.add(new Class(
                        temp.getString("start"),
                        temp.getString("stop"),
                        temp.getString("type"),
                        temp.getString("subject"),
                        temp.getString("instructor")
                ));
            }
            routine.put(today, classes);
        }catch (JSONException e){
            Log.e(TAG, "onGetJSONDataComplete: " + e.getMessage() );
        }
    }
}
