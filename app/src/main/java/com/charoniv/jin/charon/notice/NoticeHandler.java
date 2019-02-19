package com.charoniv.jin.charon.notice;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.charoniv.jin.charon.DownloadStatus;
import com.charoniv.jin.charon.GetJSONData;
import com.charoniv.jin.charon.activities.SettingsActivity;
import com.charoniv.jin.charon.adapters.NoticeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class NoticeHandler implements GetJSONData.OnGetJSONDataConplete {

    private static final String TAG = "NoticeHandler";
    private List<Notice> noticeList;
    private NoticeDatabase database;
    private NoticeAdapter noticeAdapter;

    public void setNoticeAdapter(NoticeAdapter adapter){
        this.noticeAdapter = adapter;
    }

    public NoticeHandler(Context context) {
        GetJSONData getJSONData = new GetJSONData(this);
        StringBuilder api = new StringBuilder();
        database = new NoticeDatabase(context);
        noticeList = database.getNotices();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        api.append("http://").append(preferences.getString(SettingsActivity.API, "")).append("/api/notice.php");
        getJSONData.execute(api.toString());
    }

    @Override
    public void onGetJSONDataComplete(String json, DownloadStatus downloadStatus) {
        if(downloadStatus == DownloadStatus.OK){
            try {
                noticeList.clear();
                JSONObject jsonObject = new JSONObject(json);
                JSONArray array = jsonObject.getJSONArray("notices");
                for(int i=0; i< array.length(); i++){
                    noticeList.add(new Notice(
                            array.getJSONObject(i).getInt("sn"),
                            array.getJSONObject(i).getString("info"),
                            array.getJSONObject(i).getString("date")
                    ));
                }
                database.updateNotice(noticeList);
                noticeAdapter.updateAndNotify(noticeList);
            }catch (JSONException e){
                Log.e(TAG, "onGetJSONDataComplete: " + e.getMessage());
            }
        }else{
            Log.d(TAG, "onGetJSONDataComplete: No Changes to notices");
        }
    }

    public List<Notice> getNoticeList() {
        return noticeList;
    }
}
