package com.charoniv.jin.charon;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.charoniv.jin.charon.activities.Subject;
import com.charoniv.jin.charon.activities.SettingsActivity;
import com.charoniv.jin.charon.adapters.ResourceRecyclerAdapter;
import com.charoniv.jin.charon.datasource.Resource;

import java.util.List;

public class ResourceActivity extends AppCompatActivity implements ParseJSONResource.OnParseJSONComplete{

    ProgressBar progressBar;
    RecyclerView resource_recyclerView;
    ResourceRecyclerAdapter resourceRecyclerAdapter;
    private static final String TAG = "ResourceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Resources");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.spin);
        resource_recyclerView = findViewById(R.id.resource_recyclerView);

        StringBuilder api = new StringBuilder();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        api.append("http://").append(preferences.getString(SettingsActivity.API, "charoniv.000webhostapp.com")).append("/api/resources.php");
        Log.d(TAG, "onCreate: " + api.toString());
        ParseJSONResource parseJSONResource = new ParseJSONResource(progressBar, this);
        parseJSONResource.execute(api.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.resource_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ParseJSONResource parseJSONResource = new ParseJSONResource(progressBar, this);
        switch (id){
            case R.id.action_cnas:
                parseJSONResource.setSubject(Subject.CNaS);
                break;
            case R.id.action_dsap:
                parseJSONResource.setSubject(Subject.DSAP);
                break;
            case R.id.action_ds:
                parseJSONResource.setSubject(Subject.DS);
                break;
            case R.id.action_elective:
                parseJSONResource.setSubject(Subject.E);
                break;
            case R.id.action_eeas:
                parseJSONResource.setSubject(Subject.EEaS);
                break;
            case R.id.action_pm:
                parseJSONResource.setSubject(Subject.PM);
                break;
            case R.id.action_oam:
                parseJSONResource.setSubject(Subject.OaM);
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        StringBuilder api = new StringBuilder();
        api.append("http://").append(preferences.getString(SettingsActivity.API, "charoniv.000webhostapp.com")).append("/api/resources.php");
        parseJSONResource.execute(api.toString());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onParseJSONComplete(List<Resource> resources, DownloadStatus downloadStatus) {
        if(downloadStatus == DownloadStatus.OK) {
            resourceRecyclerAdapter = new ResourceRecyclerAdapter(this, resources);
            resource_recyclerView.setLayoutManager(new LinearLayoutManager(this));
            resource_recyclerView.setAdapter(resourceRecyclerAdapter);
        }else {
            Toast.makeText(this, "No Internet Connection or Invalid Domain", Toast.LENGTH_LONG).show();
            finish();
        }
    }


}
