package edu.kirkwood.calendar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.Calendar;
public class DayViewActivity extends ParentActivity {
    private String dayValue;
    private int dayToView;
    private Button week_day1;
    private Button week_day2;
    private Button week_day3;
    private Button selected_week_day4;
    private Button week_day5;
    private Button week_day6;
    private Button week_day7;
    LocalDate todayDate;
  
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        openParentTools();

        //Gets Calendar object
        Calendar calendar = Calendar.getInstance();

        //Check for intent from week day button clicked
        Intent intent = getIntent();

        todayDate = (LocalDate) intent.getSerializableExtra("today");


        //Setting button text for week day 1
        week_day1 = findViewById(R.id.week_day1);
        String day1Text = String.valueOf(todayDate.minusDays(3).getDayOfMonth());
        week_day1.setText(day1Text);

        //Setting button text for week day 2
        week_day2 = (Button) findViewById(R.id.week_day2);
        String day2Text = String.valueOf(todayDate.minusDays(2).getDayOfMonth());
        week_day2.setText(day2Text);

        //Setting button text for week day 3
        week_day3 = (Button) findViewById(R.id.week_day3);
        String day3Text = String.valueOf(todayDate.minusDays(1).getDayOfMonth());
        week_day3.setText(day3Text);

        //Setting button text for week day 4, the selected day to view
        selected_week_day4 = (Button) findViewById(R.id.selected_week_day4);
        selected_week_day4.setText(String.valueOf(todayDate.getDayOfMonth()));

        //Setting button text for week day 5
        week_day5 = (Button) findViewById(R.id.week_day5);
        String day5Text = String.valueOf(todayDate.plusDays(1).getDayOfMonth());
        week_day5.setText(day5Text);

        //Setting button text for week day 6
        week_day6 = (Button) findViewById(R.id.week_day6);
        String day6Text = String.valueOf(todayDate.plusDays(2).getDayOfMonth());
        week_day6.setText(day6Text);

        //Setting button text for week day 7
        week_day7 = (Button) findViewById(R.id.week_day7);
        String day7Text = String.valueOf(todayDate.plusDays(3).getDayOfMonth());
        week_day7.setText(day7Text);


        setDayButtonListeners();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setDayButtonListeners() {
        //Setting button listeners
        week_day1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                viewDay(todayDate.minusDays(3));
            }
        });
        week_day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDay(todayDate.minusDays(2));
            }
        });
        week_day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDay(todayDate.minusDays(1));
            }
        });
        week_day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDay(todayDate.plusDays(1));
            }
        });
        week_day6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDay(todayDate.plusDays(2));
            }
        });
        week_day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDay(todayDate.plusDays(3));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        onRunning(2);
    }
}

