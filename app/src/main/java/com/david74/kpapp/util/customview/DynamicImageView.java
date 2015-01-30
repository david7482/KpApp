package com.david74.kpapp.util.customview;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.david74.kpapp.util.screen.Screen;
import com.makeramen.RoundedImageView;

public class DynamicImageView extends RoundedImageView {

    private double mDynamicRatio = 0.0;

    public DynamicImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicImageView(Context context) {
        super(context);
    }

    public void setDynamicRatio(double ratio) {
        if (ratio != mDynamicRatio) {
            mDynamicRatio = ratio;
            requestLayout();
        }
    }

    public double getDynamicRatio() {
        return mDynamicRatio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (mDynamicRatio > 0.0) {

            int orientation = Screen.getScreenOrientation((FragmentActivity)getContext());
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ||
                orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                int height = MeasureSpec.getSize(heightMeasureSpec);
                int width = (int) (height * mDynamicRatio);
                setMeasuredDimension(width, height);
            } else {
                int width = MeasureSpec.getSize(widthMeasureSpec);
                int height = (int) (width * mDynamicRatio);
                setMeasuredDimension(width, height);
            }
        }
        else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
