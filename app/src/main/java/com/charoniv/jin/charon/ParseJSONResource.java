package com.charoniv.jin.charon;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.charoniv.jin.charon.activities.Subject;
import com.charoniv.jin.charon.datasource.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ParseJSONResource extends AsyncTask<String, Void, List<Resource>> implements GetJSONData.OnGetJSONDataConplete{

    private static final String TAG = "ParseJSONResource";
    private List<Resource> resources;
    private OnParseJSONComplete onParseJSONComplete;
    private DownloadStatus downloadStatus;
    private final ProgressBar progressBar;
    Subject subject = null;

    void setSubject(Subject subject) {
        this.subject = subject;
    }

    interface OnParseJSONComplete{
        void onParseJSONComplete(List<Resource> resources, DownloadStatus downloadStatus);
    }

    ParseJSONResource(ProgressBar progressBar, OnParseJSONComplete onParseJSONComplete){
        this.progressBar = progressBar;
        this.onParseJSONComplete = onParseJSONComplete;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(List<Resource> resources) {
        super.onPostExecute(resources);
        progressBar.setVisibility(View.INVISIBLE);
        onParseJSONComplete.onParseJSONComplete(resources, downloadStatus);
    }

    @Override
    protected List<Resource> doInBackground(String... strings) {
        GetJSONData getJSONData = new GetJSONData(this);
        getJSONData.run(strings[0]);
        return resources;
    }

    @Override
    public void onGetJSONDataComplete(String json, DownloadStatus downloadStatus) {
        this.downloadStatus = downloadStatus;
        resources = new ArrayList<>();

        if (downloadStatus == DownloadStatus.OK) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("resources");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject temp = jsonArray.getJSONObject(i);

                    if (subject != null) {
                        if (subject == Subject.CNaS && !temp.getString("subject").equals("Computer Network and Security"))
                            continue;
                        if (subject == Subject.DSAP && !temp.getString("subject").equals("Digital Signal Analysis and Processing"))
                            continue;
                        if (subject == Subject.DS && !temp.getString("subject").equals("Distributed System"))
                            continue;
                        if (subject == Subject.E && !temp.getString("subject").equals("Elective I"))
                            continue;
                        if (subject == Subject.EEaS && !temp.getString("subject").equals("Energy Environment and Society"))
                            continue;
                        if (subject == Subject.OaM && !temp.getString("subject").equals("Organization and Management"))
                            continue;
                        if (subject == Subject.PM && !temp.getString("subject").equals("Project Management"))
                            continue;
                    }
                    resources.add(new Resource(temp.getString("title"),
                            temp.getString("date"),
                            temp.getString("subject"),
                            temp.getString("filename"),
                            temp.getString("extension"),
                            temp.getString("url"),
                            temp.getInt("sn"),
                            temp.getString("type")));
                }
            } catch (JSONException e) {
                Log.e(TAG, "onGetJSONDataComplete: " + e.getMessage());
            }
        }
    }
}