package com.qql.tdym.myapp.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.qql.tdym.myapp.R;

/**
 * Created by qiao on 2016/10/20.
 */

public class SettingDialog {
    private Context mContext;
    private AlertDialog dialog;
    private TextInputLayout period, days;
    private AppCompatTextView signDay;
    private DatePickDialog mDatePickDialog;

    public SettingDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void reset() {
        if (period != null && days != null) {
            days.getEditText().setText("");
            period.getEditText().setText("");
        }
    }

    public void show() {
        if (dialog == null) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_setting, null);
            period = (TextInputLayout) view.findViewById(R.id.period);
            days = (TextInputLayout) view.findViewById(R.id.days);
            signDay = (AppCompatTextView) view.findViewById(R.id.signDay);
            signDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDatePickDialog == null) {
                        mDatePickDialog = new DatePickDialog(mContext) {
                            @Override
                            public void pickDate(CalendarDay date) {
                                if (date != null) {
                                    signDay.setText(date.toString());
                                }
                            }
                        };
                    } else {
                        mDatePickDialog.reset();
                    }
                    mDatePickDialog.show();
                }
            });
            dialog = new AlertDialog.Builder(mContext).setTitle(R.string.dialog_setting)
                    .setView(view)
                    .setCancelable(false)
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //TODO
                        }
                    })
                    .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
        }
        dialog.show();
    }
}
