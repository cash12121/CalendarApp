package edu.kirkwood.calendar;

import android.os.Bundle;

public class EventActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        openParentTools();
    }
    @Override
    public void onStart(){
        super.onStart();
        onRunning(3);
    }
}
