package com.david74.kpapp.app;

import android.app.Application;

import com.david74.kpapp.R;
import com.david74.kpapp.util.appcontext.AppContext;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class KpApplication extends Application {

    Tracker gaTracker = null;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.set(getApplicationContext());
        initUniversalImageLoader();
    }

    // Create global configuration and initialize ImageLoader with this config
    void initUniversalImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(android.R.color.transparent)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .memoryCacheSizePercentage(50)
                .build();

        ImageLoader.getInstance().init(config);
    }

    synchronized public Tracker getTracker() {
        if (gaTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            gaTracker = analytics.newTracker(R.xml.ga_tracker);
        }
        return gaTracker;
    }
}
