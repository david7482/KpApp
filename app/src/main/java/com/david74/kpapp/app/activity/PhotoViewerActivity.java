package com.david74.kpapp.app.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;

import com.david74.kpapp.R;
import com.david74.kpapp.app.adapter.KpPhotoViewAdapter;
import com.david74.kpapp.app.model.KpPhotosModel;
import com.xgc1986.parallaxPagerTransformer.ParallaxPagerTransformer;

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

        // Get photos model from parcelable
        Bundle bundle = getIntent().getBundleExtra(KEY_BUNDLE);
        Parcelable parcelable = bundle.getParcelable(KEY_PARCELABLE);
        photosModel = Parcels.unwrap(parcelable);

        ParallaxPagerTransformer pt = new ParallaxPagerTransformer((R.id.photo));
        pt.setBorder(4);

        photoViewAdapter = new KpPhotoViewAdapter(this, getSupportFragmentManager());
        photoViewAdapter.add(photosModel.getPhotoUrlList());

        viewPager.setBackgroundColor(0xFF000000);
        viewPager.setPageTransformer(false, pt);
        viewPager.setAdapter(photoViewAdapter);
        viewPager.setCurrentItem(photosModel.getSelectedPosition());
    }
}
