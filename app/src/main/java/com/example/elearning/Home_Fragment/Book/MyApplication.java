package com.example.elearning.Home_Fragment.Book;

import android.app.Application;

import org.xutils.x;

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false); //是否输出debug日志，开启debug会影响性能。
    }
}
