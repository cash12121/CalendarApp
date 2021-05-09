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
    Button week_day1;
    LocalDate date;
    static Button week_day2;
    static Button week_day3;
    static Button selected_week_day4;
    static Button week_day5;
    static Button week_day6;
    static Button week_day7;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        openParentTools();
        //Gets Calendar object
        Calendar calendar = Calendar.getInstance();
        Intent intent = getIntent();
        //Get intent from selected day from month view
        int dayToView = intent.getIntExtra("dayOfMonth", calendar.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, dayToView);
        //Check for intent from week day button clicked
        String buttonValue = intent.getStringExtra(ParentActivity.SELECTED_DAY);
        if(buttonValue != null){
            if(buttonValue.length()!=0) {
                int dayClicked = Integer.parseInt(buttonValue);
                calendar.set(Calendar.DAY_OF_MONTH, dayClicked);
            }
        }
        //Setting calendar day of month to value selected by month view
        setDayToView(calendar.get(Calendar.DAY_OF_MONTH));
        //Setting local date
        setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        //setLocalDate(getDate());

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

        //Setting button listeners
        week_day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDay(week_day1.getText());
            }
        });
        week_day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDay(week_day2.getText());
            }
        });
        week_day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDay(week_day3.getText());
            }
        });
        week_day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDay(week_day5.getText());
            }
        });
        week_day6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDay(week_day6.getText());
            }
        });
        week_day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDay(week_day7.getText());
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setLocalDate(String ymd){
        try {
            date = LocalDate.parse(ymd);
        }
        catch (Exception DateTimeParseException){
            date = LocalDate.now();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        onRunning(2);
    }
}

