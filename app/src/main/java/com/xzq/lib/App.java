package com.xzq.lib;

import android.app.Application;
import android.support.annotation.Nullable;

import com.xzq.xtimber.XTimber;


/**
 * App
 * Created by xzq on 2018/10/22.
 */
public class App extends Application {

    private static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        //Log start
        XTimber.plant(new XTimber.DebugTree() {
            /**
             * 是否可以输出日志
             * @param tag TAG
             * @param priority 日志等级
             * @return 是否可以输出日志，利用BuildConfig.DEBUG来控制日志开关
             *         （release版本关闭日志，debug版本输出日志）
             */
            @Override
            protected boolean isLoggable(@Nullable String tag, int priority) {
                return BuildConfig.DEBUG;
            }

            /**
             * 获取Log输出方法路径的模式，默认值是{@code MethodLocation.FULL}
             *
             * @return Log输出方法路径的模式
             */
            @Override
            protected MethodLocation methodLocation() {
                //不输出方法位置，此模式输出的日志跟官方Log一样
                //return MethodLocation.NONE;
                //只输出方法名称
                //return MethodLocation.METHOD;
                //显示方法的全路径(默认模式)
                return MethodLocation.FULL;
            }
        });

//        if (BuildConfig.DEBUG){
//            XTimber.plant(new XTimber.DebugTree());
//        }

        //Log end
    }

    public static App get() {
        return INSTANCE;
    }
}
