package edu.kirkwood.calendar;

import android.os.Build;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class EventActivity extends ParentActivity {
    CalendarView calendar_start;
    CalendarView calendar_end;
    TextView date_view_start;
    TextView date_view_end;
    TextView time_view_start;
    TextView time_view_end;
    //Defining CalendarView and Textview
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        openParentTools();
        TimePicker pickerS = (TimePicker)findViewById(R.id.time_picker_start);
        pickerS.setIs24HourView(true);
        //Getting CalendarView and TextView variables
        calendar_start = (CalendarView) findViewById(R.id.calendar_start);
        date_view_start = (TextView) findViewById(R.id.date_view_start);
        time_view_start = (TextView) findViewById(R.id.time_view_start);
        //Setting date listener for the value of DAYS, MONTH, YEARS
        calendar_start.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //Stores the value of the date in a formatted String
                //Adding 1 in month because index starts with 0
                String DateS = dayOfMonth + "-" + (month+1) + "-" + year;
                date_view_start.setText(DateS);
            }
        });
        pickerS.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(@NonNull TimePicker timePicker, int hour, int min){
                String TimeS = " / " + hour + ":" + min;
                time_view_start.setText(TimeS);
            }
        });

        TimePicker pickerE = (TimePicker)findViewById(R.id.time_picker_end);
        pickerE.setIs24HourView(true);
        //Getting CalendarView and TextView variables
        calendar_end = (CalendarView) findViewById(R.id.calendar_end);
        date_view_end = (TextView) findViewById(R.id.date_view_end);
        time_view_end = (TextView) findViewById(R.id.time_view_end);
        //Setting date listener for the value of DAYS, MONTH, YEARS
        calendar_end.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //Stores the value of the date in a formatted String
                //Adding 1 in month because index starts with 0
                String DateE = dayOfMonth + "-" + (month+1) + "-" + year;
                //Set date in TextView, ie display
                date_view_end.setText(DateE);
            }
        });
        pickerE.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(@NonNull TimePicker timePicker, int hour, int min){
                String TimeE = " / " + hour + ":" + min;
                time_view_start.setText(TimeE);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        onRunning(3);
    }
}
