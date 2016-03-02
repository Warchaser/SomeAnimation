package com.example.someanimation.App;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by WuShengbo on 2016/3/1.
 */
public class APP extends Application
{
    private RefWatcher refWatcher;

    @Override
    public void onCreate()
    {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context)
    {
        APP application = (APP) context.getApplicationContext();
        return application.refWatcher;
    }

}
