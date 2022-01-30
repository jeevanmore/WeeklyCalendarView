package com.jaswikventures.customcalendarview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {

    private TextView eventDateTV, eventTimeTV;
    private EditText eventNameET;

    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
    }

    private void initWidgets() {

        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
        eventNameET = findViewById(R.id.eventNameET);

    }

    public void setEventAction(View view) {
        String eventName = eventNameET.getText().toString();
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate,time);
        Event.eventList.add(newEvent);
        finish();
    }
}