package com.david74.kpapp.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.david74.kpapp.R;
import com.david74.kpapp.app.adapter.KpAlbumAdapter;
import com.david74.kpapp.app.custom.ClickRecyclerView;
import com.david74.kpapp.app.model.KpAlbumModel;
import com.david74.kpapp.app.presenter.KpAlbumListPresenter;
import com.david74.kpapp.app.presenter.KpAlbumListPresenterImp;
import com.david74.kpapp.app.model.Model;

import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class KpAlbumFragement extends BaseFragment implements KpAlbumControl {

    @InjectView(R.id.loading_progress)
    ProgressBar progressBar;

    @InjectView(R.id.recycler_view_albums)
    ClickRecyclerView albumsRecyclerView;

    private KpAlbumAdapter kpAlbumAdapter;
    private LinearLayoutManager layoutManager;
    private KpAlbumListPresenter kpAlbumListPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        kpAlbumAdapter = new KpAlbumAdapter();
        layoutManager = new LinearLayoutManager(getActivity());
        kpAlbumListPresenter = new KpAlbumListPresenterImp();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_entry, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        albumsRecyclerView.setAdapter(kpAlbumAdapter);
        albumsRecyclerView.setLayoutManager(layoutManager);
        albumsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        albumsRecyclerView.setOnItemClickListener(new ClickRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                KpAlbumModel albumModel = (KpAlbumModel)kpAlbumAdapter.get(position);
                EventBus.getDefault().post(albumModel);
            }
        });

        kpAlbumListPresenter.setView(this);
        kpAlbumListPresenter.initialize();
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
}
