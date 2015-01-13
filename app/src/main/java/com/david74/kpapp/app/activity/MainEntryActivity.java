package com.david74.kpapp.app.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.david74.kpapp.R;
import com.david74.kpapp.app.KpApplication;
import com.david74.kpapp.app.adapter.KpAlbumAdapter;
import com.david74.kpapp.app.custom.ClickRecyclerView;
import com.david74.kpapp.app.control.KpAlbumControl;
import com.david74.kpapp.app.itemanimator.SlideInLeftItemAnimator;
import com.david74.kpapp.app.model.KpAlbumModel;
import com.david74.kpapp.app.model.Model;
import com.david74.kpapp.app.presenter.KpAlbumListPresenter;
import com.david74.kpapp.app.presenter.KpAlbumListPresenterImp;
import com.david74.kpapp.util.appcontext.AppContext;
import com.david74.kpapp.util.screen.Screen;

import com.google.android.gms.analytics.HitBuilders;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.parceler.Parcels;

import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;


public class MainEntryActivity extends BaseActivity implements KpAlbumControl {

    @InjectView(R.id.loading_progress)
    CircularProgressBar progressBar;

    @InjectView(R.id.recycler_view_albums)
    ClickRecyclerView albumsRecyclerView;

    private KpAlbumAdapter kpAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main_entry);
        super.onCreate(savedInstanceState);

        kpAlbumAdapter = new KpAlbumAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        {
            int orientation = Screen.getScreenOrientation(this);
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ||
                orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            } else {
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            }
        }

        albumsRecyclerView.setLayoutManager(layoutManager);
        albumsRecyclerView.setItemAnimator(new SlideInLeftItemAnimator());
        albumsRecyclerView.setAdapter(kpAlbumAdapter);
        albumsRecyclerView.setOnItemClickListener(new ClickRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, final int position, long id) {
                AppContext.runOnMainUiThread(new Runnable() {
                    @Override
                    public void run() {
                        KpAlbumModel albumModel = (KpAlbumModel) kpAlbumAdapter.get(position);
                        EventBus.getDefault().post(albumModel);
                    }
                }, getResources().getInteger(R.integer.ripple_duration));
            }
        });

        KpAlbumListPresenter kpAlbumListPresenter = new KpAlbumListPresenterImp();
        kpAlbumListPresenter.setView(this);
        kpAlbumListPresenter.initialize();
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

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void add(Model model) {
        kpAlbumAdapter.add(model);
    }

    @Override
    public void add(List<Model> modelList) {
        kpAlbumAdapter.add(modelList);
    }

    public void onEventMainThread(KpAlbumModel albumModel) {

        Parcelable parcelable = Parcels.wrap(albumModel);
        Intent intent = new Intent(this, AlbumDetailActivity.class);
        intent.putExtra(AlbumDetailActivity.KEY_PARCELABLE, parcelable);

        ((KpApplication)AppContext.get()).getTracker().send(
                new HitBuilders.EventBuilder().setCategory("OpenAlbum").setAction(albumModel.getTitle()).build());

        startActivity(intent);
    }
}
