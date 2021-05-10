package edu.kirkwood.calendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.kirkwood.calendar.dataobjects.Event;

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
    private LocalDate todayDate;
    private ArrayList<Event> events;
    private LinearLayout scrollEvents;
    private int counter = 0;
  
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

        //Get Events
        events = new ArrayList<Event>();
        events.add(new Event("Test", "", LocalDateTime.now(), LocalDateTime.now()));

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
            ArrayList<Event> events1 = gson.fromJson(jsonEvents, type);

            for (Event eve : events1) {
                events.add(eve);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }

        //Add Events
        scrollEvents = findViewById(R.id.event_scroll);

        for (Event event : events) {
            if(event.getEventDate().isEqual(todayDate)) {
                Button button = new Button(new ContextThemeWrapper(this, R.style.Theme_AppCompat_DayNight));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, getDpMeasure(10), 0,0);
                button.setLayoutParams(params);
                button.setGravity(Gravity.CENTER);
                button.setText(event.getTitle());
                button.setId(counter++);
                Context context = this;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle(event.getTitle())
                                .setMessage(event.getTimeString())
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });

                scrollEvents.addView(button);
            }
        }




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

    private int getDpMeasure(int dp) {
        int px = 0 ;

        Resources resources = getResources();
        px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.getDisplayMetrics()
        );

        return px;
    }

    @Override
    protected void onStart() {
        super.onStart();
        onRunning(2);
    }
}

