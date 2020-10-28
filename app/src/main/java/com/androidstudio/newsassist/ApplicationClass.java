package com.androidstudio.newsassist;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static final ArrayList<News> news = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
