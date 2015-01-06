package com.david74.kpapp.app.activity;

import android.os.Bundle;

import com.david74.kpapp.R;
import com.david74.kpapp.app.fragment.KpAlbumDetailFragment;

public class AlbumDetailActivity extends BaseActivity {

    public static String KEY_PARCELABLE = "parcelable";
    public static String KEY_BUNDLE = "bundle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        addAlbumDetailFragment();
    }

    private void addAlbumDetailFragment() {
        KpAlbumDetailFragment fragment = KpAlbumDetailFragment.newInstance(getIntent().getBundleExtra(KEY_BUNDLE));
        getFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, fragment)
                .commit();
    }
}
