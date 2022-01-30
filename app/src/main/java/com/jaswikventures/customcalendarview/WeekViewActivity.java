package com.jaswikventures.customcalendarview;

import static com.jaswikventures.customcalendarview.CalendarUtils.daysInMonthArray;
import static com.jaswikventures.customcalendarview.CalendarUtils.daysInWeekArray;
import static com.jaswikventures.customcalendarview.CalendarUtils.monthYearFromDate;
import static com.jaswikventures.customcalendarview.CalendarUtils.selectedDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private RecyclerView calendarRecyclerView;
    private TextView monthYearText;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);

        initWidgets();
        setWeekView();
    }


    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTv);
        eventListView = findViewById(R.id.eventListView);

    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);

        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();

    }

    public void previousWeekAction(View view) {
        selectedDate = selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        selectedDate = selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        setEventAdapter();
    }

    private void setEventAdapter() {

        ArrayList<Event> dailyEvents = Event.eventsForDate(selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        selectedDate = date;
        setWeekView();
    }

    public void newEventAction(View view) {
        startActivity(new Intent(this, EventEditActivity.class));
    }

}