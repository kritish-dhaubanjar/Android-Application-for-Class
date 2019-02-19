package com.charoniv.jin.charon;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetJSONData extends AsyncTask<String, Void, String> {

    private static final String TAG = "GetJSONData";
    private OnGetJSONDataConplete onGetJSONDataConplete;
    private DownloadStatus downloadStatus;

    public GetJSONData(OnGetJSONDataConplete onGetJSONDataConplete) {
        this.onGetJSONDataConplete = onGetJSONDataConplete;
    }

    public interface OnGetJSONDataConplete{
        void onGetJSONDataComplete(String json, DownloadStatus downloadStatus);
    }
    
    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        StringBuilder json = new StringBuilder();

        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            if(connection.getResponseCode() == 200) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                for (String raw = bufferedReader.readLine(); raw != null; raw = bufferedReader.readLine()) {
                    json.append(raw);
                }
                return json.toString();
            }else{

            }
            Log.d(TAG, "doInBackground: " + json.toString());
        }catch (MalformedURLException e){
            Log.e(TAG, "doInBackground: " + e.getMessage() );
        }catch (IOException e){
            Log.e(TAG, "doInBackground: " + e.getMessage() );
        }finally {
            if(connection!=null)
                connection.disconnect();
            if(bufferedReader !=null)
                try {
                    bufferedReader.close();
                }catch (IOException e){
                    Log.e(TAG, "doInBackground: " + e.getMessage() );
                }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        if(s!=null)
            downloadStatus = DownloadStatus.OK;
        else
            downloadStatus = DownloadStatus.ERROR;

        onGetJSONDataConplete.onGetJSONDataComplete(s, downloadStatus);
        super.onPostExecute(s);
    }

    void run(String url){
        onPostExecute(doInBackground(url));
    }
}
