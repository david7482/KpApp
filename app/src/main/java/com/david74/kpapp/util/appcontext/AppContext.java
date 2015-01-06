package com.david74.kpapp.util.appcontext;

import android.content.Context;
import android.os.Handler;

public class AppContext {
    private static Context sAppContext;
    private static Handler sMainUiThreadHandler;

    public static Context get() {
        return sAppContext;
    }

    public static void set(Context appContext) {
        sAppContext = appContext;
        sMainUiThreadHandler = new Handler();
    }

    public static void runOnMainUiThread(Runnable runnable) {
        sMainUiThreadHandler.post(runnable);
    }

    public static void runOnMainUiThread(Runnable runnable, long delayMillis) {
        sMainUiThreadHandler.postDelayed(runnable, delayMillis);
    }
}
