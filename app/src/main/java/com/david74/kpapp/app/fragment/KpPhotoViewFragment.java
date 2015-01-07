package com.david74.kpapp.app.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.david74.kpapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import butterknife.InjectView;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class KpPhotoViewFragment extends BaseFragment {

    public static String KEY_PHOTO_URL = "photo_url";

    @InjectView(R.id.photo)
    ImageView photo;

    @InjectView(R.id.loading_progress)
    CircularProgressBar progressBar;

    public static Fragment newInstance(String photoUrl) {

        Bundle bundle = new Bundle();
        bundle.putString(KEY_PHOTO_URL, photoUrl);

        KpPhotoViewFragment fragment = new KpPhotoViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public KpPhotoViewFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String photoUrl = getArguments().getString(KEY_PHOTO_URL);
        ImageLoader.getInstance().displayImage(photoUrl, photo, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
