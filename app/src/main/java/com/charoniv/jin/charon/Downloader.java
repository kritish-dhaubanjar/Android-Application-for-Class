package com.charoniv.jin.charon;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.support.v4.content.FileProvider.getUriForFile;

public class Downloader extends AsyncTask<String, Integer, DownloadStatus> {

    private static final String TAG = "Downloader";
    private Context context;
    private ProgressBar progressBar;
    private Button download;
    private String fileWithExtension;

    public Downloader(final Context context, final ProgressBar progressBar, final Button download, String filename, String extension) {
        this.context = context;
        this.progressBar = progressBar;
        this.download = download;
        this.fileWithExtension = filename + "." + extension;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
        if(download!=null)
            download.setEnabled(false);
        progressBar.setMax(100);
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(DownloadStatus status) {

        if(download !=null) {
            download.setText(context.getResources().getText(R.string.action_open));
            download.setEnabled(true);
            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFile();
                }
            });
        }
        progressBar.setVisibility(View.INVISIBLE);
        if(status == DownloadStatus.OK){
            Toast.makeText(context, "Download Complete", Toast.LENGTH_LONG).show();
            openFile();
        }else if(status == DownloadStatus.ERROR){
            Toast.makeText(context, "No Internet Connection.", Toast.LENGTH_LONG).show();
        }
        super.onPostExecute(status);
    }

    private void openFile(){
        /*File file = new File(Environment.getExternalStorageDirectory() + "/Download/" + fileWithExtension);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        */

        final File path = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "");
        final File file = new File(path, fileWithExtension );

        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(intent);
    }

    @Override
    protected DownloadStatus doInBackground(String... strings) {

        HttpURLConnection connection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte [] bytes = new byte[32];
        long totalSize = 0 , count = 0, totalDownloaded = 0;

        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            Log.d(TAG, "doInBackground: " + connection.getResponseCode());
            inputStream = connection.getInputStream();
            /*outputStream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/Download/" + fileWithExtension);*/
            File path = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "");
            File file = new File(path, fileWithExtension);
            Log.d(TAG, "doInBackground: " + FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file));
            outputStream = new FileOutputStream(file);
            totalSize = connection.getContentLength();

            while ((count = inputStream.read(bytes)) != -1) {
                totalDownloaded += count;
                outputStream.write(bytes);
                int progress = (int) ((totalDownloaded * 100 / totalSize) );
                publishProgress(progress);
            }

            return DownloadStatus.OK;
        }catch (MalformedURLException e){
            Log.d(TAG, "doInBackground MalformedURLException: " + e.getMessage());
        }catch (IOException e){
            Log.d(TAG, "doInBackground IOException: " + e.getMessage());
            return DownloadStatus.ERROR;
        }finally {
            if(connection != null)
                connection.disconnect();
            try{
                if(inputStream !=null )
                    inputStream.close();
                if(outputStream !=null)
                    outputStream.close();
            }catch (IOException e){
                Log.d(TAG, "doInBackground finally: " + e.getMessage());
            }
        }


        return DownloadStatus.OK;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }
}
