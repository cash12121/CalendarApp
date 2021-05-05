package edu.kirkwood.calendar;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;

public class EventActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        openParentTools();
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onStart(){
        super.onStart();
        onRunning(3);
    }
}
