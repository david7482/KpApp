package com.david74.kpapp.util.screen;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import com.david74.kpapp.util.appcontext.AppContext;

public class Screen {

    public static int convertDpToPixel(Context context, float dp){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int)px;
    }

    public static int convertPixelsToDp(Context context, float px){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return (int)dp;
    }

    public static int getScreenOrientation(FragmentActivity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int orientation;
        // if the device's natural orientation is portrait:
        if ((rotation == Surface.ROTATION_0
                || rotation == Surface.ROTATION_180) && height > width ||
                (rotation == Surface.ROTATION_90
                        || rotation == Surface.ROTATION_270) && width > height) {
            switch(rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_180:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                case Surface.ROTATION_270:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
            }
        }
        // if the device's natural orientation is landscape or if the device
        // is square:
        else {
            switch(rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_180:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                case Surface.ROTATION_270:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
            }
        }

        return orientation;
    }

    public static Point getScreenSize() {

        WindowManager wm = (WindowManager) AppContext.get().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size;
    }
}
