package com.david74.kpapp.util.logger;

public class Logger {

    public static enum Mode
    {
        AppName,
        AppNameWithClass,
        AppNameWithMethod
    }

    private static Mode mode = Mode.AppNameWithClass;
    private static boolean enabled = true;
    private static String appName = "KpApp";

    public static void setEnable(boolean enabled)
    {
        Logger.enabled = enabled;
    }

    public static void setMode(Mode mode)
    {
        Logger.mode = mode;
    }

    public static void setAppName(String appName)
    {
        Logger.appName = appName;
    }

    public static void i(String message, Object... args)
    {
        if (enabled)
            android.util.Log.i(getTag(), getMessage(message, args));
    }

    public static void i(Throwable e, String message, Object... args)
    {
        if (enabled)
            android.util.Log.i(getTag(), getMessage(message, args), e);
    }

    public static void e(String message, Object... args)
    {
        if (enabled)
            android.util.Log.e(getTag(), getMessage(message, args));
    }

    public static void e(Throwable e, String message, Object... args)
    {
        if (enabled)
            android.util.Log.e(getTag(), getMessage(message, args), e);
    }

    public static void d(String message, Object... args)
    {
        if (enabled)
            android.util.Log.d(getTag(), getMessage(message, args));
    }

    public static void d(Throwable e, String message, Object... args)
    {
        if (enabled)
            android.util.Log.d(getTag(), getMessage(message, args), e);
    }

    public static void v(String message, Object... args)
    {
        if (enabled)
            android.util.Log.v(getTag(), getMessage(message, args));
    }

    public static void v(Throwable e, String message, Object... args)
    {
        if (enabled)
            android.util.Log.v(getTag(), getMessage(message, args), e);
    }

    public static void w(String message, Object... args)
    {
        if (enabled)
            android.util.Log.w(getTag(), getMessage(message, args));
    }

    public static void w(Throwable e, String message, Object... args)
    {
        if (enabled)
            android.util.Log.w(getTag(), getMessage(message, args), e);
    }

    private static String getMessage(String message, Object... args) {
        // If no varargs are supplied, treat it as a request to log the string without formatting.
        return args.length == 0 ? message : String.format(message, args);
    }

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
                if (name.equals(Logger.class.getName()))
                {
                    findSelf = true;
                }
            }
            else
            {
                if (!name.equals(Logger.class.getName()))
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