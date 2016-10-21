package com.qql.tdym.myapp;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by qiao on 2016/10/20.
 */

public class MyApp extends Application {
    private static final String Tag_sp = "tag_sp";
    private static final String Tag_period = "tag_period";
    private static final String Tag_days = "tag_days";
    private static final int default_period = 28;
    private static final int default_days = 5;
    private static Application instance;
    private SharedPreferences sp;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sp = this.getSharedPreferences(Tag_sp, Activity.MODE_PRIVATE);
    }

    public void setPeriod(int period) {
        sp.edit().putInt(Tag_period, period).commit();
    }

    public int getPeriod(){
       return sp.getInt(Tag_period,default_period);
    }

    public void setDays(int days) {
        sp.edit().putInt(Tag_days, days).commit();
    }

    public int getDays(){
        return sp.getInt(Tag_days,default_days);
    }
}
