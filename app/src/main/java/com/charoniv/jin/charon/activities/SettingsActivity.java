package com.charoniv.jin.charon.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.charoniv.jin.charon.R;

public class SettingsActivity extends AppCompatActivity {

    public static String API = "API";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView apiSource = findViewById(R.id.apiSource);
        Button changeApi_button = findViewById(R.id.changeApi_button);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.contains(API))
            apiSource.setText(sharedPreferences.getString(API, "charoniv.000webhostapp.com"));

        changeApi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newApi = apiSource.getText().toString();
                sharedPreferences.edit().putString(API, newApi).apply();
                apiSource.clearFocus();
                finish();
            }
        });
    }


}
