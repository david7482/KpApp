package com.david74.kpapp.app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.david74.kpapp.app.KpApplication;
import com.david74.kpapp.util.appcontext.AppContext;
import com.google.android.gms.analytics.GoogleAnalytics;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }
}
