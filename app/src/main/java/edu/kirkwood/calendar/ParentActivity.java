package edu.kirkwood.calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;


public class ParentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openParentTools();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_element, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.calendar_settings:
                // User chose the "Settings" item, show the app settings UI
                return true;
            case R.id.calendar_view:
                // User chose the "View" item, show the calendar view select UI
                return true;
            case R.id.day_item:
                // User chose "Day View" under the calendar view submenu
                openDayActivity();
                return true;
            case R.id.month_item:
                // User chose "Month View" under the calendar view submenu
                openMonthActivity();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void openParentTools() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEventActivity();
            }
        });
    }

    // Method to create an Intent to open DayActivity
    // Should only be called if an event listener wants to open this Activity.
    private void openDayActivity() {
        Intent intent = new Intent(this, DayActivity.class);
        startActivity(intent);
    }
    private void openMonthActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void openEventActivity() {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }
}
