package com.david74.kpapp.app.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.david74.kpapp.R;
import com.david74.kpapp.app.activity.AlbumDetailActivity;
import com.david74.kpapp.app.adapter.KpPhotoAdapter;
import com.david74.kpapp.app.custom.ClickRecyclerView;
import com.david74.kpapp.app.itemanimator.SlideInLeftItemAnimator;
import com.david74.kpapp.app.model.KpAlbumModel;
import com.david74.kpapp.app.model.KpPhotoModel;
import com.david74.kpapp.app.model.KpPhotosModel;
import com.david74.kpapp.app.model.Model;
import com.david74.kpapp.app.presenter.KpAlbumDetailPresenter;
import com.david74.kpapp.app.presenter.KpAlbumDetailPresenterImp;
import com.david74.kpapp.util.appcontext.AppContext;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.parceler.Parcels;

import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class KpAlbumDetailFragment extends BaseFragment implements KpAlbumDetailControl {

    @InjectView(R.id.album_cover_image)
    ImageView albumImage;

    @InjectView(R.id.album_cover_title)
    TextView albumTitle;

    @InjectView(R.id.recycler_view_photos)
    ClickRecyclerView photosRecycleView;

    @InjectView(R.id.loading_progress)
    ProgressBar progressBar;

    private KpPhotoAdapter kpPhotoAdapter;
    private GridLayoutManager gridLayoutManager;
    private KpAlbumDetailPresenter kpAlbumDetailPresenter;
    private KpAlbumModel albumModel;

    public static KpAlbumDetailFragment newInstance(Bundle bundle) {
        KpAlbumDetailFragment fragment = new KpAlbumDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public KpAlbumDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get album model from parcelable
        Bundle bundle = getArguments();
        Parcelable parcelable = bundle.getParcelable(AlbumDetailActivity.KEY_PARCELABLE);
        albumModel = Parcels.unwrap(parcelable);

        kpPhotoAdapter = new KpPhotoAdapter();
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        kpAlbumDetailPresenter = new KpAlbumDetailPresenterImp(albumModel.getId());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        albumTitle.setText(albumModel.getTitle());
        ImageLoader.getInstance().displayImage(albumModel.getImageUrl(), albumImage);

        photosRecycleView.setAdapter(kpPhotoAdapter);
        photosRecycleView.setLayoutManager(gridLayoutManager);
        photosRecycleView.setItemAnimator(new SlideInLeftItemAnimator());
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

        kpAlbumDetailPresenter.setView(this);
        kpAlbumDetailPresenter.initialize();
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
}
