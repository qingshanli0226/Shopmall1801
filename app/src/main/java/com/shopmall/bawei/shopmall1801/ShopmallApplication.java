package com.shopmall.bawei.shopmall1801;

import android.app.Application;

public class ShopmallApplication extends Application {

    public static ShopmallApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
}
