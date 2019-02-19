package com.charoniv.jin.charon.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.charoniv.jin.charon.R;
import com.charoniv.jin.charon.calendar.KeyEvents;
import com.charoniv.jin.charon.calendar.NepaliCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AcademicCalendarActivity extends AppCompatActivity {

    Calendar calendar;
    List<TextView> days;
    TextView month_year;
    NepaliCalendar nepaliCalendar;
    int year, month, date;
    static int prev_next_month, prev_next_year;
    ListView key_dates_listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar);
        nepaliCalendar = new NepaliCalendar();

        month_year = findViewById(R.id.month_year);
        key_dates_listView = findViewById(R.id.key_dates_listView);


        days = new ArrayList<>();
        days.add((TextView) findViewById(R.id.d1));
        days.add((TextView)findViewById(R.id.d2));
        days.add((TextView)findViewById(R.id.d3));
        days.add((TextView)findViewById(R.id.d4));
        days.add((TextView)findViewById(R.id.d5));
        days.add((TextView)findViewById(R.id.d6));
        days.add((TextView)findViewById(R.id.d7));
        days.add((TextView)findViewById(R.id.d8));
        days.add((TextView)findViewById(R.id.d9));
        days.add((TextView)findViewById(R.id.d10));

        days.add((TextView)findViewById(R.id.d11));
        days.add((TextView)findViewById(R.id.d12));
        days.add((TextView)findViewById(R.id.d13));
        days.add((TextView)findViewById(R.id.d14));
        days.add((TextView)findViewById(R.id.d15));
        days.add((TextView)findViewById(R.id.d16));
        days.add((TextView)findViewById(R.id.d17));
        days.add((TextView)findViewById(R.id.d18));
        days.add((TextView)findViewById(R.id.d19));
        days.add((TextView)findViewById(R.id.d20));

        days.add((TextView)findViewById(R.id.d21));
        days.add((TextView)findViewById(R.id.d22));
        days.add((TextView)findViewById(R.id.d23));
        days.add((TextView)findViewById(R.id.d24));
        days.add((TextView)findViewById(R.id.d25));
        days.add((TextView)findViewById(R.id.d26));
        days.add((TextView)findViewById(R.id.d27));
        days.add((TextView)findViewById(R.id.d28));
        days.add((TextView)findViewById(R.id.d29));
        days.add((TextView)findViewById(R.id.d30));

        days.add((TextView)findViewById(R.id.d31));
        days.add((TextView)findViewById(R.id.d32));
        days.add((TextView)findViewById(R.id.d33));
        days.add((TextView)findViewById(R.id.d34));
        days.add((TextView)findViewById(R.id.d35));

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH); //0,1
        date = calendar.get(Calendar.DATE);

        prev_next_year = Calendar.YEAR;
        prev_next_month = Calendar.MONTH;

        setCalendar(year, month, date);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.month_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.revert_month:
                setCalendar(year, month, date);
                break;
            case R.id.kartik:
                setCalendar(2018, Calendar.OCTOBER, 24);
                break;
            case R.id.mangsir:
                setCalendar(2018, Calendar.NOVEMBER, 24);
                break;
            case R.id.poush:
                setCalendar(2018, Calendar.DECEMBER, 24);
                break;
            case R.id.magh:
                setCalendar(2019, Calendar.JANUARY, 24);
                break;
            case R.id.falgun:
                setCalendar(2019, Calendar.FEBRUARY, 24);
                break;
            case R.id.chaitra:
                setCalendar(2019, Calendar.MARCH, 24);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setCalendar(int year, int month, int date){

        NepaliCalendar.NepaliDate nepaliDate = nepaliCalendar.engToNep(year, month + 1, date);
        int total = nepaliCalendar.getTotalDaysInNepaliMonth(nepaliDate.getYear(), nepaliDate.getMonth());

        String monthYear = nepaliDate.getNepaliMonth() + " " + nepaliDate.getYear();
        month_year.setText(monthYear);

        KeyEvents keyEvents = new KeyEvents();
        List<String> events = keyEvents.getEventList(nepaliDate.getNepaliMonth());

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.event_layout, events);
        key_dates_listView.setAdapter(arrayAdapter);

        int day = 1;

        for(int i=0; i<days.size(); i++){
            if(i < nepaliDate.getStartDay()-1) {
                days.get(i).setText("");
                continue;
            }
            String dayString = "" + day;
            TextView di = days.get(i);
            di.setText(dayString);

            if(day == nepaliDate.getDate() && month == this.month){
                di.setTextColor(getResources().getColor(R.color.colorPrimary));
                di.setTypeface(null, Typeface.BOLD);
            }
            day++;

            if(day > total + 1) {
                days.get(i).setText("");
            }
        }
    }



}
