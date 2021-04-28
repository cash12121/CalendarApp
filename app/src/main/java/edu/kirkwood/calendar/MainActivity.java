package edu.kirkwood.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    // Method to create an Intent to open DayActivity
    // Should only be called if an event listener wants to open this Activity.
    private void openDayActivity() {
        Intent intent = new Intent(this, DayActivity.class);
        startActivity(intent);
    }

}