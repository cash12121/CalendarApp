package edu.kirkwood.calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EventActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        openParentTools();

        Spinner spinnerY = (Spinner) findViewById(R.id.year_spinner);
        ArrayAdapter<CharSequence> adapterY = ArrayAdapter.createFromResource(this,
                R.array.year_array, android.R.layout.simple_spinner_item);
        adapterY.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerY.setAdapter(adapterY);

        Spinner spinnerM = (Spinner) findViewById(R.id.month_spinner);
        ArrayAdapter<CharSequence> adapterM = ArrayAdapter.createFromResource(this,
                R.array.month_array, android.R.layout.simple_spinner_item);
        adapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerM.setAdapter(adapterM);

        Spinner spinnerD = (Spinner) findViewById(R.id.day_spinner);
        ArrayAdapter<CharSequence> adapterD = ArrayAdapter.createFromResource(this,
                R.array.day_array, android.R.layout.simple_spinner_item);
        adapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerD.setAdapter(adapterD);

        Spinner spinnerH = (Spinner) findViewById(R.id.hour_spinner);
        ArrayAdapter<CharSequence> adapterH = ArrayAdapter.createFromResource(this,
                R.array.hour_array, android.R.layout.simple_spinner_item);
        adapterH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerH.setAdapter(adapterH);
    }

    @Override
    public void onStart(){
        super.onStart();
        onRunning(3);
    }
}
