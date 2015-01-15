package com.david74.kpapp.util.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.david74.kpapp.util.appcontext.AppContext;

public abstract class BasePreference {

    protected abstract String getSharepreferencesName();

    private SharedPreferences sharedPreferences = null;
    protected BasePreference() {
        sharedPreferences = AppContext.get().getSharedPreferences(getSharepreferencesName(), Context.MODE_PRIVATE);
    }

    public void setValue(BasePreferenceKey key, Object value) {

        if(value instanceof String){
            putString(key.getKey(), value.toString());
        }else if(value instanceof Integer){
            putInt(key.getKey(), (Integer) value);
        }else if(value instanceof Long){
            putLong(key.getKey(), (Long) value);
        }else if(value instanceof Boolean){
            putBoolean(key.getKey(), (Boolean) value);
        }else if(value instanceof Float){
            putFloat(key.getKey(), (Float) value);
        }
    }

    public <T>T getValue(BasePreferenceKey key) {

        Object value = null;
        if(key.getType() == String.class){
            value = getString(key.toString(), (String)key.getDefault());
        }else if(key.getType() == Integer.class){
            value = getInt(key.toString(), (int)key.getDefault());
        }else if(key.getType() == Long.class){
            value = getLong(key.toString(), (long)key.getDefault());
        }else if(key.getType() == Boolean.class){
            value = getBoolean(key.toString(), (boolean)key.getDefault());
        }else if(key.getType() == Float.class){
            value = getFloat(key.toString(), (float)key.getDefault());
        }

        return (T)value;
    }

    public boolean isContains(BasePreferenceKey key){
        return isContains(key.getKey());
    }

    public boolean remove(BasePreferenceKey key){
        return remove(key.getKey());
    }

    private void putString(String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private String getString(String key, String defValue){
        return sharedPreferences.getString(key, defValue);
    }

    private void putInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    private void putLong(String key, long value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    private long getLong(String key, long defValue){
        return sharedPreferences.getLong(key, defValue);
    }

    private void putBoolean(String key, boolean value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private boolean getBoolean(String key, boolean defValue){
        return sharedPreferences.getBoolean(key, defValue);
    }

    private void putFloat(String key, float value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    private float getFloat(String key, float defValue){
        return sharedPreferences.getFloat(key, defValue);
    }

    private boolean isContains(String key){
        return sharedPreferences.contains(key);
    }

    private boolean remove(String key){
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.commit();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}