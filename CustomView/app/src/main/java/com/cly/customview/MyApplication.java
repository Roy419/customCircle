package com.cly.customview;

import android.app.Application;
import android.content.Context;

/**
 * Created by admin on 2018/3/25.
 */

public class MyApplication extends Application {
   public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
      context = this;
    }
}
