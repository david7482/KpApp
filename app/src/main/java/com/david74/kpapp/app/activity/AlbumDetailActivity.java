package com.david74.kpapp.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.david74.kpapp.R;
import com.david74.kpapp.app.fragment.KpAlbumDetailFragment;
import com.david74.kpapp.app.model.KpPhotosModel;

import org.parceler.Parcels;

import de.greenrobot.event.EventBus;

public class AlbumDetailActivity extends BaseActivity {

    public static String KEY_PARCELABLE = "parcelable";
    public static String KEY_BUNDLE = "bundle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        addAlbumDetailFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void addAlbumDetailFragment() {
        KpAlbumDetailFragment fragment = KpAlbumDetailFragment.newInstance(getIntent().getBundleExtra(KEY_BUNDLE));
        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, fragment)
                .commit();
    }

    public void onEventMainThread(KpPhotosModel photosModel) {

        Bundle bundle = new Bundle();
        Parcelable parcelable = Parcels.wrap(photosModel);
        bundle.putParcelable(PhotoViewerActivity.KEY_PARCELABLE, parcelable);

        Intent intent = new Intent(this, PhotoViewerActivity.class);
        intent.putExtra(PhotoViewerActivity.KEY_BUNDLE, bundle);

        startActivity(intent);
    }
}
