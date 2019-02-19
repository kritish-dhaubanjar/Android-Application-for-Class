package com.charoniv.jin.charon.activities;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.charoniv.jin.charon.BuildConfig;
import com.charoniv.jin.charon.Downloader;
import com.charoniv.jin.charon.NotificationService;
import com.charoniv.jin.charon.R;
import com.charoniv.jin.charon.ResourceActivity;
import com.charoniv.jin.charon.adapters.RoutineRecyclerAdapter;
import com.charoniv.jin.charon.datasource.Routine;

import java.io.File;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    RecyclerView routine_recyclerView;
    RoutineRecyclerAdapter routineRecyclerAdapter;
    Routine routine;
    final Calendar calendar = Calendar.getInstance();
    public static int dayInt;
    Toolbar toolbar;
    ProgressBar download_syllabus;
    FloatingActionButton fab;
    private static final String TAG = "MainActivity";
    public static NotificationService notificationService;

    private final int REQUEST_CODE = 102;
    public static boolean readWriteExternal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dayInt = calendar.get(Calendar.DAY_OF_WEEK);
        setToolbarTitle(dayInt);
        fab = findViewById(R.id.refresh);

        if(notificationService!=null)
            notificationService.onDestroy();

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            readWriteExternal = true;
        }else{
            String [] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
        }

        routine_recyclerView = findViewById(R.id.routine_recyclerView);
        routine = new Routine(this);
        routineRecyclerAdapter = new RoutineRecyclerAdapter(this, routine.getItems(dayInt));

        routine.setRecyclerAdapter(routineRecyclerAdapter);
        routine_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        routine_recyclerView.setAdapter(routineRecyclerAdapter);
        download_syllabus = findViewById(R.id.download_syllabus);
        download_syllabus.setIndeterminate(false);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Please wait", Toast.LENGTH_SHORT).show();
                routine.updateDatabase();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                    //
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.next && dayInt < Calendar.THURSDAY){
            routineRecyclerAdapter.onRequest(routine.getItems(++dayInt));
            setToolbarTitle(dayInt);
            return true;
        }else if(id == R.id.prev && dayInt > Calendar.SUNDAY){
            routineRecyclerAdapter.onRequest(routine.getItems(--dayInt));
            setToolbarTitle(dayInt);
            return true;
        }else if(id == R.id.today){
            dayInt = calendar.get(Calendar.DAY_OF_WEEK);
            routineRecyclerAdapter.onRequest(routine.getItems(dayInt));
            setToolbarTitle(dayInt);
            return true;
        }else if(id == R.id.action_resources){
            Intent intent = new Intent(this, ResourceActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_calendar) {
            Intent intent = new Intent(this, AcademicCalendarActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_notice){
            Intent intent = new Intent(this, NoticeActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_syllabus ){
            //File file = new File(Environment.getExternalStorageDirectory() + "/Download/BCT_IV_I.pdf");

            File path = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "");
            File file = new File(path, "BCT_IV_I.pdf");
            Uri uri = FileProvider.getUriForFile(getBaseContext(), BuildConfig.APPLICATION_ID + ".fileprovider", file);

            if(file.exists()) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(intent);
            }else {
                Downloader downloader = new Downloader(this, download_syllabus, null, "BCT_IV_I", "pdf");
                downloader.execute("https://kec.edu.np/wp-content/uploads/2016/04/BCT_IV_I.pdf");
            }
        }
        return super.onOptionsItemSelected(item);
    }


    void setToolbarTitle(int dayInt){
        String day;
        switch (dayInt){
            case Calendar.SUNDAY:
                day = "Sunday";
                break;
            case Calendar.MONDAY:
                day = "Monday";
                break;
            case Calendar.TUESDAY:
                day = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                day = "Wednesday";
                break;
            case Calendar.THURSDAY:
                day = "Thursday";
                break;
            case Calendar.FRIDAY:
                day = "Friday";
                break;
            case Calendar.SATURDAY:
                day = "Saturday";
                break;
            default:
                day = "";
        }
        if (dayInt == Calendar.FRIDAY || dayInt == Calendar.SATURDAY){
            Toast.makeText(this, "Charon says, \"No Routine for Today!\"", Toast.LENGTH_LONG).show();

        }
        toolbar.setTitle("Charon (" + day + ")");
        setSupportActionBar(toolbar);
    }
}
