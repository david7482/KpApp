package com.david74.kpapp.util;

public class L
{
    public static enum Mode
    {
        AppName,
        AppNameWithClass,
        AppNameWithMethod
    };

    private static Mode mode = Mode.AppNameWithClass;
    private static boolean enabled = true;
    private static String appName = "kpapp";

    public static void setEnable(boolean _enabled)
    {
        enabled = _enabled;
    }

    public static void setMode(Mode _mode)
    {
        mode = _mode;
    }

    public static void setAppName(String _appName)
    {
        appName = _appName;
    }

    public static void i(String message)
    {
        if (enabled)
            android.util.Log.i(getTag(), message);
    }

    public static void i(String message, Throwable e)
    {
        if (enabled)
            android.util.Log.i(getTag(), message, e);
    }

    public static void e(String message)
    {
        if (enabled)
            android.util.Log.e(getTag(), message);
    }

    public static void e(String message, Throwable e)
    {
        if (enabled)
            android.util.Log.e(getTag(), message, e);
    }

    public static void d(String message)
    {
        if (enabled)
            android.util.Log.d(getTag(), message);
    }

    public static void d(String message, Throwable e)
    {
        if (enabled)
            android.util.Log.d(getTag(), message, e);
    }

    public static void v(String message)
    {
        if (enabled)
            android.util.Log.v(getTag(), message);
    }

    public static void v(String message, Throwable e)
    {
        if (enabled)
            android.util.Log.v(getTag(), message, e);
    }

    public static void w(String message)
    {
        if (enabled)
            android.util.Log.w(getTag(), message);
    }

    public static void w(String message, Throwable e)
    {
        if (enabled)
            android.util.Log.w(getTag(), message, e);
    }

    @SuppressWarnings("incomplete-switch")
    private static String getTag()
    {
        StackTraceElement[] st = null;
        String[] caller = null;
        switch (mode)
        {
            case AppNameWithClass:
                st = Thread.currentThread().getStackTrace();
                if (st != null && st.length >= 4)
                {
                    caller = getCallerInfo(st);
                    return appName + "-" + caller[0];
                }
                break;
            case AppNameWithMethod:
                st = Thread.currentThread().getStackTrace();
                if (st != null && st.length >= 4)
                {
                    caller = getCallerInfo(st);
                    return appName + "-" + caller[0] + ":" + caller[1];
                }
                break;
        }
        return appName;
    }

    private static String[] getCallerInfo(StackTraceElement[] st)
    {
        boolean findSelf = false;
        String[] info = new String[] { "Unknown", "Unknown" };

        for (StackTraceElement e : st)
        {
            String name = e.getClassName();
            if (!findSelf)
            {
                if (name.equals(L.class.getName()))
                {
                    findSelf = true;
                }
            }
            else
            {
                if (!name.equals(L.class.getName()))
                {
                    String[] s = name.split("\\.");
                    info[0] = s[s.length - 1];
                    info[1] = e.getMethodName();
                    break;
                }
            }
        }

        return info;
    }
}