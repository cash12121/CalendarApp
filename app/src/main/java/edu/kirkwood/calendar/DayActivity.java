package edu.kirkwood.calendar;

import android.os.Bundle;

public class DayActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        openParentTools();
    }

    @Override
    protected void openMonthActivity() {

    }

    @Override
    protected void openDayActivity() {

    }

    @Override
    public void onStart(){
        super.onStart();
        onRunning(2);
    }
}