package edu.kirkwood.calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.time.LocalDate;

public class MainActivity extends ParentActivity {
    //Defining CalendarView and Textview
    CalendarView simpleCalendarView;
    TextView date_view;
    private LocalDate date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openParentTools();
        //Getting CalendarView and TextView variables
        simpleCalendarView = (CalendarView) findViewById(R.id.calendar);
        date_view = (TextView) findViewById(R.id.date_view);
        //Setting date listener for the value of DAYS, MONTH, YEARS
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //Stores the value of the date in a formatted String
                //Adding 1 in month because index starts with 0
                String Date = dayOfMonth + "-" + (month+1) + "-" + year;
                //Set date in TextView, ie display
                date_view.setText(Date);
            }
        });
    }
    @Override
    public void onStart(){
        super.onStart();
        onRunning(1);
    }
}