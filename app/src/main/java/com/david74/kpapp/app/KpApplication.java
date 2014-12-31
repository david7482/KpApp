package com.david74.kpapp.app;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class KpApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
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
                .build();

        ImageLoader.getInstance().init(config);
    }
}
