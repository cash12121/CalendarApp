package edu.kirkwood.calendar;

import android.os.Bundle;

public class DayActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        openParentTools();
    }
}