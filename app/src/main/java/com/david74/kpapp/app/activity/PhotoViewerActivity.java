package com.david74.kpapp.app.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;

import com.david74.kpapp.R;
import com.david74.kpapp.app.adapter.KpPhotoViewAdapter;
import com.david74.kpapp.app.model.KpPhotosModel;
import com.david74.kpapp.util.screen.Screen;
import com.david74.kpapp.util.transformer.ParallaxPagerTransformer;

import org.parceler.Parcels;

import butterknife.InjectView;

public class PhotoViewerActivity extends BaseActivity {

    public static String KEY_PARCELABLE = "parcelable";
    public static String KEY_BUNDLE = "bundle";

    @InjectView(R.id.photo_view_pager)
    ViewPager viewPager;

    private KpPhotoViewAdapter photoViewAdapter;
    private KpPhotosModel photosModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_photo_viewer);
        super.onCreate(savedInstanceState);

        // Hide action bar
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Get photos model from parcelable
        Bundle bundle = getIntent().getBundleExtra(KEY_BUNDLE);
        Parcelable parcelable = bundle.getParcelable(KEY_PARCELABLE);
        photosModel = Parcels.unwrap(parcelable);

        // Prepare parallax transformer
        ParallaxPagerTransformer pt = new ParallaxPagerTransformer((R.id.photo));
        pt.setSpeed(0.4f);

        photoViewAdapter = new KpPhotoViewAdapter(this, getSupportFragmentManager());
        photoViewAdapter.add(photosModel.getPhotoUrlList());

        viewPager.setBackgroundColor(getResources().getColor(R.color.parallax_view_pager_background));
        viewPager.setPageMargin(Screen.convertDpToPixel(this, 8));
        viewPager.setPageTransformer(false, pt);
        viewPager.setAdapter(photoViewAdapter);
        viewPager.setCurrentItem(photosModel.getSelectedPosition());
    }
}
