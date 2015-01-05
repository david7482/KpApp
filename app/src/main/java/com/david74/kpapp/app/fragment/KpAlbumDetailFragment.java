package com.david74.kpapp.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
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
import com.david74.kpapp.app.model.Model;
import com.david74.kpapp.app.presenter.KpAlbumDetailPresenter;
import com.david74.kpapp.app.presenter.KpAlbumDetailPresenterImp;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.InjectView;

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

        kpPhotoAdapter = new KpPhotoAdapter();
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        kpAlbumDetailPresenter = new KpAlbumDetailPresenterImp(getArguments().getString(AlbumDetailActivity.KEY_ID));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        {
            albumTitle.setText(bundle.getString(AlbumDetailActivity.KEY_TITLE));
            ImageLoader.getInstance().displayImage(bundle.getString(AlbumDetailActivity.KEY_IMAGE_URL), albumImage);
        }

        photosRecycleView.setAdapter(kpPhotoAdapter);
        photosRecycleView.setLayoutManager(gridLayoutManager);
        photosRecycleView.setItemAnimator(new SlideInLeftItemAnimator());
        photosRecycleView.setOnItemClickListener(new ClickRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {

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
