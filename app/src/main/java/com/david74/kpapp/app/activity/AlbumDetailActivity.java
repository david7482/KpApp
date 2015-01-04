package com.david74.kpapp.app.activity;

import android.os.Bundle;

import com.david74.kpapp.R;
import com.david74.kpapp.app.fragment.KpAlbumDetailFragment;

public class AlbumDetailActivity extends BaseActivity {

    public static String KEY_ID = "id";
    public static String KEY_TITLE = "title";
    public static String KEY_IMAGE_URL = "imageUrl";
    public static String KEY_ALBUM_BUNDLE = "albumBundle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        addAlbumDetailFragment();
    }

    private void addAlbumDetailFragment() {
        KpAlbumDetailFragment fragment = KpAlbumDetailFragment.newInstance(getIntent().getBundleExtra(KEY_ALBUM_BUNDLE));
        getFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, fragment)
                .commit();
    }
}
