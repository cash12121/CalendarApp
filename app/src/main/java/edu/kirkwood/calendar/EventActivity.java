package edu.kirkwood.calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import edu.kirkwood.calendar.dataobjects.Event;

public class EventActivity extends ParentActivity {
    private DatePickerDialog startDatePickerDialog;
    private Button startDateButton;
    private DatePickerDialog endDatePickerDialog;
    private Button endDateButton;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Button startTimeButton;
    private Button endTimeButton;
    private int startHour;
    private int startMin;
    private int endHour;
    private int endMin;
    private Button saveButton;
    private EditText eventTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        openParentTools();
        initDatePickers();
        startDateButton = findViewById(R.id.startDatePickerButton);
        startDateButton.setText(getTodayDate());
        endDateButton = findViewById(R.id.endDatePickerButton);
        endDateButton.setText(getTodayDate());
        startTimeButton = findViewById(R.id.startTimeButton);
        endTimeButton = findViewById(R.id.endTimeButton);
        saveButton = findViewById(R.id.saveButton);
        eventTitle = findViewById(R.id.event_title);
    }

    private void initDatePickers()
    {
        DatePickerDialog.OnDateSetListener dateStartSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                startDateButton.setText(date);
                startDate = LocalDate.of(year, month, day);
            }
        };

        DatePickerDialog.OnDateSetListener dateEndSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                endDateButton.setText(date);
                endDate = LocalDate.of(year, month, day);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        startDatePickerDialog = new DatePickerDialog(this, style, dateStartSetListener, year, month, day);
        endDatePickerDialog = new DatePickerDialog(this, style, dateEndSetListener, year, month, day);

    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        switch (month) {
            case 1:
                return "JAN";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "APR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AUG";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            case 12:
                return "DEC";
            default:
                return "JAN";
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        onRunning(3);
    }

    public void openStartDatePicker(View view) {
        startDatePickerDialog.show();
    }

    public void openEndDatePicker(View view) {
        endDatePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void openStartTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
            startHour = selectedHour;
            startMin = selectedMinute;
            startTimeButton.setText(String.format(Locale.getDefault(), "%02d:%02d",startHour, startMin));
            startTime = LocalTime.of(startHour, startMin);
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, startHour, startMin, true);

        timePickerDialog.setTitle("Select Start Time");
        timePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void openEndTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
            endHour = selectedHour;
            endMin = selectedMinute;
            endTimeButton.setText(String.format(Locale.getDefault(), "%02d:%02d",endHour, endMin));
            endTime = LocalTime.of(endHour, endMin);
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, endHour, endMin, true);

        timePickerDialog.setTitle("Select Start Time");
        timePickerDialog.show();
    }
  
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onSaveClick(View view) {
        String title;
        String desc = "";
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;

        title = eventTitle.getText().toString();
        try {
            startTime = LocalDateTime.of(startDate, this.startTime);
            endTime = LocalDateTime.of(endDate, this.endTime);
        } catch (Exception ex) {
            ex.getMessage();
        }

        Event newEvent = new Event(title, desc, startTime, endTime);
        ArrayList<Event> list = new ArrayList<Event>();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());

        // Attempt to get SharedPreferences
        try {
            SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
            Gson gson = gsonBuilder.setPrettyPrinting().create();
            String jsonEvents = appSharedPrefs.getString("Events","");
            Type type = new TypeToken<List<Event>>(){}.getType();
            //String helper = gson.fromJson(jsonEvents, type);
            ArrayList<Event> events = gson.fromJson(jsonEvents, type);

            for (Event eve : events) {
                list.add(eve);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }

        list.add(newEvent);

        // Try to store in SharedPreferences
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefEditor = appSharedPrefs.edit();

        Gson gson = gsonBuilder.setPrettyPrinting().create();
        String eventsJson = gson.toJson(list);
        prefEditor.putString("Events", eventsJson);
        prefEditor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
