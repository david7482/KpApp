package com.david74.kpapp.app.custom;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.david74.kpapp.util.screen.Screen;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int spaceInPixel;

    public SpacesItemDecoration(Context context, int space) {
        this.spaceInPixel = Screen.convertDpToPixel(context, space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = spaceInPixel;
        outRect.right = spaceInPixel;
        outRect.bottom = spaceInPixel;

        // Add top margin only for the first item to avoid double space between items
        if(parent.getChildPosition(view) == 0) {
            outRect.top = spaceInPixel;
        }
    }
}
