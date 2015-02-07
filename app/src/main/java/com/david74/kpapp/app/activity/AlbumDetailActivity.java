package com.david74.kpapp.app.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.david74.kpapp.R;
import com.david74.kpapp.app.adapter.KpPhotoAdapter;
import com.david74.kpapp.app.custom.ClickRecyclerView;
import com.david74.kpapp.app.control.KpAlbumDetailControl;
import com.david74.kpapp.app.custom.DividerItemDecoration;
import com.david74.kpapp.app.custom.SpacesItemDecoration;
import com.david74.kpapp.app.itemanimator.SlideInLeftItemAnimator;
import com.david74.kpapp.app.model.KpAlbumModel;
import com.david74.kpapp.app.model.KpPhotosModel;
import com.david74.kpapp.app.model.Model;
import com.david74.kpapp.app.presenter.KpAlbumDetailPresenter;
import com.david74.kpapp.app.presenter.KpAlbumDetailPresenterImp;
import com.david74.kpapp.util.appcontext.AppContext;
import com.david74.kpapp.util.screen.Screen;

import org.parceler.Parcels;

import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class AlbumDetailActivity extends BaseActivity implements KpAlbumDetailControl {

    public static String KEY_PARCELABLE = "parcelable";

    @InjectView(R.id.recycler_view_photos)
    ClickRecyclerView photosRecycleView;

    @InjectView(R.id.loading_progress)
    CircularProgressBar progressBar;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private KpPhotoAdapter kpPhotoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_album_detail);
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        // Get album model from parcelable
        Intent intent = getIntent();
        Parcelable parcelable = intent.getParcelableExtra(AlbumDetailActivity.KEY_PARCELABLE);
        KpAlbumModel albumModel = Parcels.unwrap(parcelable);

        kpPhotoAdapter = new KpPhotoAdapter();

        // Setup the layout manager
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        {
            int orientation = Screen.getScreenOrientation(this);
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ||
                orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
            } else {
                layoutManager.setOrientation(GridLayoutManager.VERTICAL);
            }

            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                return ((position - 2) % 3 == 0) ? 4 : 2;
                }
            });
        }

        // Add divider
        Drawable divider = getResources().getDrawable(R.drawable.shape_recycler_view_divider);
        photosRecycleView.addItemDecoration(new DividerItemDecoration(divider, DividerItemDecoration.HORIZONTAL));
        photosRecycleView.addItemDecoration(new DividerItemDecoration(divider, DividerItemDecoration.VERTICAL));

        photosRecycleView.setAdapter(kpPhotoAdapter);
        photosRecycleView.setLayoutManager(layoutManager);
        photosRecycleView.setOnItemClickListener(new ClickRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, final int position, long id) {
                AppContext.runOnMainUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<Model> modelList = kpPhotoAdapter.getAll();
                        KpPhotosModel photosModel = new KpPhotosModel(position, modelList);
                        EventBus.getDefault().post(photosModel);
                    }
                }, getResources().getInteger(R.integer.ripple_duration));
            }
        });

        KpAlbumDetailPresenter kpAlbumDetailPresenter = new KpAlbumDetailPresenterImp(albumModel.getId());
        kpAlbumDetailPresenter.setView(this);
        kpAlbumDetailPresenter.initialize();
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
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void add(Model model) {
        kpPhotoAdapter.add(model);
    }

    @Override
    public void add(List<Model> modelList) {
        kpPhotoAdapter.add(modelList);
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
