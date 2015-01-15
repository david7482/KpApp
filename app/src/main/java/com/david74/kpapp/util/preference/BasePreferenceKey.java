package com.david74.kpapp.util.preference;

public interface BasePreferenceKey {
    public String getKey();
    public Class getType();
    public Object getDefault();
}
