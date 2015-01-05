package com.david74.kpapp.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.david74.kpapp.R;
import com.david74.kpapp.app.model.KpAlbumModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.greenrobot.event.EventBus;


public class MainEntryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_entry);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_clear_cache) {
            ImageLoader.getInstance().clearMemoryCache();
            ImageLoader.getInstance().clearDiskCache();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEventMainThread(KpAlbumModel albumModel) {

        // TODO: We might need to figure out other way to pass album model
        Bundle bundle = new Bundle();
        bundle.putString(AlbumDetailActivity.KEY_ID, albumModel.getId());
        bundle.putString(AlbumDetailActivity.KEY_TITLE, albumModel.getTitle());
        bundle.putString(AlbumDetailActivity.KEY_IMAGE_URL, albumModel.getImageUrl());

        Intent intent = new Intent(this, AlbumDetailActivity.class);
        intent.putExtra(AlbumDetailActivity.KEY_ALBUM_BUNDLE, bundle);

        startActivity(intent);
    }
}
