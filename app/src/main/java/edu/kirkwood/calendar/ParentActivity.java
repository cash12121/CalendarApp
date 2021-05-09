package edu.kirkwood.calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class ParentActivity extends AppCompatActivity {
    private String dateText = "";
    private int dayToView=0;
    public static final String SELECTED_DAY = "edu.kirkwood.calendar.DATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openParentTools();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (ir != 3) {
            getMenuInflater().inflate(R.menu.menu_element, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_event, menu);
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.confirm:
                if (ir == 3) {
                    openMonthActivity();
                } else {
                    return true;
                }
            case R.id.cancel:
                if (ir == 3) {
                    openMonthActivity();
                } else {
                    return true;
                }
            case R.id.add_event:
                if (ir == 3) {
                    return true;
                } else {
                    openEventActivity();
                }
                return true;
            case R.id.calendar_view:
                // User chose the "View" item, show the calendar view select UI
                if (ir == 1) {
                    openDayActivity();
                } else if (ir == 2) {
                    openMonthActivity();
                } else {
                    return true;
                }

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    //Used to test which activity is currently running
    public int ir;

    public void onRunning(int isRunning) {
        ir = isRunning;
    }

    public void openParentTools() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    protected void openMonthActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("dayOfMonth", dayToView);
        startActivity(intent);
    }

    // Method to create an Intent to open DayActivity
    protected void openDayActivity() {
        Intent intent = new Intent(this, DayViewActivity.class);
        intent.putExtra("dayOfMonth", dayToView);
        startActivity(intent);
    }

    // Should only be called if an event listener wants to open this Activity.
    private void openEventActivity() {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    //Method to create an Intent to open DayViewActivity with changed
    public void viewDay(CharSequence butText) {
        Intent intent = new Intent(this, DayViewActivity.class);
        Button clicked = new Button(this);
        String dayClicked = (String) butText;
        intent.putExtra(SELECTED_DAY, dayClicked);
        startActivity(intent);
    }
    //Methods to set and get the day user wants to view
    public void setDayToView(int d){
        dayToView = d;
    }
    //Methods to set and get Calendar object
    public String getDate() {
        return dateText;
    }
    public void setDate(int day, int month, int year) {
        dateText = year + "-" + month + "-" + day;
    }
}
