package com.david74.kpapp.app.activity;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

public abstract class BaseActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectViews();
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }
}
