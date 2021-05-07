package edu.kirkwood.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DayViewActivity extends AppCompatActivity {
    Calendar calendar;
    int dayValue;

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
        //Setting the text as the current day for the fourth button, current selected day to view
        dayValue = calendar.get(Calendar.DAY_OF_MONTH);
        Button selected_week_day4 = (Button) findViewById(R.id.selected_week_day4);
        selected_week_day4.setText(dayValue);
        //Setting the button text for the previous and upcoming week days
        //Setting button text for previous week days
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        dayValue=calendar.get(Calendar.DAY_OF_MONTH);
        Button week_day3 = (Button) findViewById(R.id.week_day3);
        week_day3.setText(dayValue);

        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        dayValue=calendar.get(Calendar.DAY_OF_MONTH);
        Button week_day2 = (Button) findViewById(R.id.week_day2);
        week_day2.setText(dayValue);

        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        dayValue=calendar.get(Calendar.DAY_OF_MONTH);
        Button week_day1 = (Button) findViewById(R.id.week_day1);
        week_day1.setText(dayValue);

        //Setting button text for upcoming week days
        calendar.roll(Calendar.DAY_OF_MONTH, 4);
        dayValue=calendar.get(Calendar.DAY_OF_MONTH);
        Button week_day5 = (Button) findViewById(R.id.week_day5);
        week_day3.setText(dayValue);

        calendar.roll(Calendar.DAY_OF_MONTH, 1);
        dayValue=calendar.get(Calendar.DAY_OF_MONTH);
        Button week_day6 = (Button) findViewById(R.id.week_day6);
        week_day2.setText(dayValue);

        calendar.roll(Calendar.DAY_OF_MONTH, 1);
        dayValue=calendar.get(Calendar.DAY_OF_MONTH);
        Button week_day7 = (Button) findViewById(R.id.week_day7);
        week_day1.setText(dayValue);
    }
}

