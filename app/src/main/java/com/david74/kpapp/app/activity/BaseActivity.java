package com.david74.kpapp.app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity
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
