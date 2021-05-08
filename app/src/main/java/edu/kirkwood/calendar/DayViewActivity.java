package edu.kirkwood.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DayViewActivity extends AppCompatActivity {
    Calendar calendar;
    String dayValue;
    static Button week_day1;
    static Button week_day2;
    static Button week_day3;
    static Button selected_week_day4;
    static Button week_day5;
    static Button week_day6;
    static Button week_day7;
    public DayViewActivity() {
        calendar = Calendar.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        //Check for intent from week day button clicked
        if(getIntent().getExtras() != null) {
            Intent intent = getIntent();
            int dayClicked = Integer.parseInt(intent.getStringExtra(MainActivity.SELECTED_DAY));
            calendar.set(Calendar.DAY_OF_MONTH, dayClicked);
        }
        //Setting button text for week day 4, the selected day to view
        dayValue = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        selected_week_day4 = (Button) findViewById(R.id.selected_week_day4);
        selected_week_day4.setText(dayValue);
        //Setting button text for week day 3
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        dayValue = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        week_day3 = (Button) findViewById(R.id.week_day3);
        week_day3.setText(dayValue);
        //Setting button text for week day 2
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        dayValue = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        week_day2 = (Button) findViewById(R.id.week_day2);
        week_day2.setText(dayValue);
        //Setting button text for week day 1
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        dayValue = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        week_day1 = (Button) findViewById(R.id.week_day1);
        week_day1.setText(dayValue);
        //Setting button text for week day 5
        calendar.roll(Calendar.DAY_OF_MONTH, 4);
        dayValue = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        week_day5 = (Button) findViewById(R.id.week_day5);
        week_day5.setText(dayValue);
        //Setting button text for week day 6
        calendar.roll(Calendar.DAY_OF_MONTH, 1);
        dayValue = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        week_day6 = (Button) findViewById(R.id.week_day6);
        week_day6.setText(dayValue);
        //Setting button text for week day 7
        calendar.roll(Calendar.DAY_OF_MONTH, 1);
        dayValue = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        week_day7 = (Button) findViewById(R.id.week_day7);
        week_day7.setText(dayValue);
    }
}

