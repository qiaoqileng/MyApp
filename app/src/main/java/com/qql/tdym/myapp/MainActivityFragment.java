package com.qql.tdym.myapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.qql.tdym.myapp.eventbus.EventReturnToday;

import java.util.Date;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View rootView;
    private MaterialCalendarView calendarView;
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        calendarView = (MaterialCalendarView) rootView.findViewById(R.id.calendarView);
        calendarView.setDateSelected(new Date(System.currentTimeMillis()),true);
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Snackbar.make(widget,date.getYear()+"年"+date.getMonth()+"月"+date.getDay()+"日",Snackbar.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEventMainThread(EventReturnToday event){
        if(event != null && calendarView != null){
            calendarView.setDateSelected(CalendarDay.today(),true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
