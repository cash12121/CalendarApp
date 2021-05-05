package edu.kirkwood.calendar;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class EventActivity extends ParentActivity {
    //Defining CalendarView and Textview
    CalendarView calendar_start;
    CalendarView calendar_end;
    TextView date_view_start;
    TextView date_view_end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        openParentTools();
        //Getting CalendarView and TextView variables
        calendar_start = (CalendarView) findViewById(R.id.calendar_start);
        date_view_start = (TextView) findViewById(R.id.date_view_start);
        //Setting date listener for the value of DAYS, MONTH, YEARS
        calendar_start.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //Stores the value of the date in a formatted String
                //Adding 1 in month because index starts with 0
                String Date = dayOfMonth + "-" + (month+1) + "-" + year;
                //Set date in TextView, ie display
                date_view_start.setText(Date);
            }
        });
        //Getting CalendarView and TextView variables
        calendar_end = (CalendarView) findViewById(R.id.calendar_end);
        date_view_end = (TextView) findViewById(R.id.date_view_end);
        //Setting date listener for the value of DAYS, MONTH, YEARS
        calendar_end.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //Stores the value of the date in a formatted String
                //Adding 1 in month because index starts with 0
                String Date = dayOfMonth + "-" + (month+1) + "-" + year;
                //Set date in TextView, ie display
                date_view_end.setText(Date);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        onRunning(3);
    }
}
