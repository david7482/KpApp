package com.david74.kpapp.util.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.david74.kpapp.util.appcontext.AppContext;

public class PreferUtil {

    private static SharedPreferences mSharedPreferences = null;
    private static final String SHAREPREFERENCES_NAME = "KpApp";

    private PreferUtil() {

    }

    public static enum SharedKeys {

        // information center
        IC_FIRST_NOTIFICATION(Boolean.class),
        IC_REPORT_NOTIFICATION_STATUS(String.class),
        IC_LAST_NOTIFICATION_ID(Long.class),

        // aws
        DEVICE_ACTIVATED_VERSION(String.class),
        LAST_UPDATE_DEVICE_INFO(String.class),
        LAST_TIME_UPDATE_USER_ACTION(Long.class),

        // aws force update
        AWS_FORCE_UPDATE_TYPE(Integer.class),
        AWS_FORCE_UPDATE_TIME(String.class),
        AWS_FORCE_UPDATE_GAME_LAUNCH_COUNT(Integer.class),

        // facebook
        SOCIAL_FB_ID(String.class),
        SOCIAL_FB_USER_NAME(String.class),
        SOCIAL_FB_EVER_ACTIVATED(Boolean.class),

        // Game Profile
        MARS_GAME_PROFILE_REFRESH_TIME(Long.class)
        ;

        private Class type;
        SharedKeys(Class type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return String.format("SHAREDKEY_%s", this.name());
        }

        public Class getType() {
            return type;
        }
    }

    public static void setSharedValue(SharedKeys key, Object value) {

        if(value instanceof String){
            putString(key.toString(), value.toString());
        }else if(value instanceof Integer){
            putInt(key.toString(), (Integer) value);
        }else if(value instanceof Long){
            putLong(key.toString(), (Long) value);
        }else if(value instanceof Boolean){
            putBoolean(key.toString(), (Boolean) value);
        }else if(value instanceof Float){
            putFloat(key.toString(), (Float) value);
        }
    }

    public static <T>T setSharedValue(SharedKeys key) {

        Object value = null;
        if(key.getType() == String.class){
            value = getString(key.toString());
        }else if(key.getType() == Integer.class){
            value = getInt(key.toString());
        }else if(key.getType() == Long.class){
            value = getLong(key.toString());
        }else if(key.getType() == Boolean.class){
            value = getBoolean(key.toString());
        }else if(key.getType() == Float.class){
            value = getFloat(key.toString());
        }

        return (T)value;
    }

    public static boolean isContains(SharedKeys key){
        return isContains(key.toString());
    }

    public static boolean remove(SharedKeys key){
        return remove(key.toString());
    }

    public static void removeAll() {
        for (SharedKeys key:SharedKeys.values()) {
            remove(key);
        }
    }

    static{
        PreferUtil.init(AppContext.get());
    }

    private static void init(Context context){
        mSharedPreferences = context.getSharedPreferences(SHAREPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    private static void putString(String key, String value){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static String getString(String key){
        return mSharedPreferences.getString(key, "");
    }

    private static void putInt(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private static int getInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    private static void putLong(String key, long value){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    private static long getLong(String key){
        return mSharedPreferences.getLong(key, 0);
    }

    private static void putBoolean(String key, boolean value){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private static boolean getBoolean(String key){
        return mSharedPreferences.getBoolean(key, false);
    }

    private static void putFloat(String key, float value){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    private static float getFloat(String key){
        return mSharedPreferences.getFloat(key, 0.f);
    }

    private static boolean isContains(String key){
        return mSharedPreferences.contains(key);
    }

    private static boolean remove(String key){
        try {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.remove(key);
            editor.commit();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
