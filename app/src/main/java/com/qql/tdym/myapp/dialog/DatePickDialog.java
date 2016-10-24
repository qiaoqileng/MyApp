package com.qql.tdym.myapp.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.qql.tdym.myapp.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by qiao on 2016/10/21.
 */
public abstract class DatePickDialog {

    private Context context;
    private AlertDialog dialog;
    private MaterialCalendarView calendarView;

    public DatePickDialog(Context context) {
        this.context = context;
    }

    public void reset() {
        if (calendarView != null) {
            calendarView.clearSelection();
        }
    }

    public void show() {
        if (dialog == null) {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_date_pick, null);
            calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(System.currentTimeMillis()));
            System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//今天的日期
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 11);
            calendarView.state().edit()
                    .setMaximumDate(CalendarDay.today())
                    .setMinimumDate(calendar)
                    .commit();
            calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                @Override
                public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                    if (selected) {
                        pickDate(date);
                        dialog.dismiss();
                    }
                }
            });
            dialog = new AlertDialog.Builder(context).setTitle(R.string.dialog_setting)
                    .setView(view)
                    .setCancelable(true)
                    .create();
        }
        dialog.show();
    }

    public abstract void pickDate(CalendarDay date);
}
