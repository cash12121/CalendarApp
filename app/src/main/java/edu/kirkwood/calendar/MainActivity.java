package edu.kirkwood.calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
public class MainActivity extends ParentActivity {
    //Defining CalendarView and Textview
    CalendarView simpleCalendarView;
    Calendar cal = Calendar.getInstance();
    TextView date_view;
    private String dateText;
    private int selectedDay;
    private int dayToView;
    private LocalDate date;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openParentTools();
        //Getting CalendarView and TextView variables
        simpleCalendarView = (CalendarView) findViewById(R.id.calendar);
        date_view = (TextView) findViewById(R.id.date_view);
        //Getting date
        Intent intent = getIntent();
        selectedDay = intent.getIntExtra("dayOfMonth", cal.get(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.DAY_OF_MONTH, selectedDay);
        simpleCalendarView.setDate(cal.getTimeInMillis());
        setDate(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
        dateText = getDate();

        //setLocalDate(dateText);

        //Setting date listener for the value of DAYS, MONTH, YEARS
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //Stores the value of the date in a formatted String
                //Adding 1 in month because index starts with 0
                setDate(dayOfMonth, month, year);
                //setLocalDate(getDate());
                dateText = year+"-"+month+"-"+dayOfMonth;
                setDayToView(dayOfMonth);
                //Set date in TextView, ie display
                date_view.setText(dateText);

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
    public void onStart(){
        super.onStart();
        onRunning(1);
    }
}