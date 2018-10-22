package com.xzq.lib;

import android.app.Application;

/**
 * App
 * Created by xzq on 2018/10/22.
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App get() {
        return instance;
    }
}
