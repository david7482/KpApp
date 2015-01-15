package com.david74.kpapp.util.preference.ui;

import com.david74.kpapp.util.preference.BasePreference;

public class UiPreference extends BasePreference {

    private static UiPreference batteryPreference = null;
    public static UiPreference getInstance() {
        if (batteryPreference == null) {
            batteryPreference = new UiPreference();
        }
        return batteryPreference;
    }

    private UiPreference() {

    }

    @Override
    protected String getSharepreferencesName() {
        return "ui_preference";
    }
}
