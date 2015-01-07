package com.david74.kpapp.util.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

public class ParallaxPagerTransformer implements ViewPager.PageTransformer {
    private int id;
    private float speed = 0.2f;

    public ParallaxPagerTransformer(int id) {
        this.id = id;
    }

    @Override
    public void transformPage(View view, float position) {

        View parallaxView = view.findViewById(id);

        if (parallaxView != null) {
            if (position > -1 && position < 1) {
                float width = parallaxView.getWidth();
                parallaxView.setTranslationX(-(position * width * speed));
            }
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
