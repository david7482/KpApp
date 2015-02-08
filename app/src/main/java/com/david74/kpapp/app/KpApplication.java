package com.david74.kpapp.app;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.david74.kpapp.R;
import com.david74.kpapp.db.KpAlbumRecord;
import com.david74.kpapp.db.KpPhotoRecord;
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
        initActiveAndroidDB();
        initUniversalImageLoader();
    }

    @Override
    public void onTerminate() {
        deInitActiveAndroidDB();
        super.onTerminate();
    }

    // Create global configuration and initialize ImageLoader with this config
    void initUniversalImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.color.material_grey_800)
                .showImageForEmptyUri(R.color.material_grey_800)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .memoryCacheSizePercentage(50)
                .build();

        ImageLoader.getInstance().init(config);
    }

    void initActiveAndroidDB() {
        Configuration.Builder config = new Configuration.Builder(this);
        config.setDatabaseName("KpApp.db")
                .setDatabaseVersion(1)
                .addModelClass(KpAlbumRecord.class)
                .addModelClass(KpPhotoRecord.class);

        ActiveAndroid.initialize(config.create());
    }

    void deInitActiveAndroidDB() {
        ActiveAndroid.dispose();
    }

    synchronized public Tracker getTracker() {
        if (gaTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            gaTracker = analytics.newTracker(R.xml.ga_tracker);
        }
        return gaTracker;
    }
}
