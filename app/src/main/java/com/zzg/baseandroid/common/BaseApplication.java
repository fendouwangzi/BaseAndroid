package com.zzg.baseandroid.common;

import android.app.Application;

import com.zzg.baseandroid.util.LogUtils;

/**
 * @author zhongzhigang
 * @Description:
 * @date 2017/11/2
 */
public class BaseApplication extends Application {
    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        LogUtils.init();
    }

    public static BaseApplication getInstance() {
        return baseApplication;
    }

}
