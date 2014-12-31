package com.david74.kpapp.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.david74.kpapp.R;
import com.david74.kpapp.api.KpApiCaller;
import com.david74.kpapp.api.model.KpAlbumInfoWrapper;
import com.david74.kpapp.app.adapter.AlbumAdapter;
import com.david74.kpapp.app.custom.ClickRecyclerView;

import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class KpAlbumFragement extends BaseFragment {

    @InjectView(R.id.loading_progress)
    ProgressBar progressBar;

    @InjectView(R.id.recycler_view_albums)
    ClickRecyclerView albumsRecyclerView;

    private AlbumAdapter albumAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        albumAdapter = new AlbumAdapter();
        layoutManager = new LinearLayoutManager(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kp_album, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        albumsRecyclerView.setAdapter(albumAdapter);
        albumsRecyclerView.setLayoutManager(layoutManager);
        albumsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        loadAlbum();
    }

    private void loadAlbum() {

        showLoading();

        KpApiCaller.getApiCaller().getAlbumListAsync(new Callback<KpAlbumInfoWrapper>() {
            @Override
            public void success(KpAlbumInfoWrapper kpAlbumInfoWrapper, Response response) {
                if (kpAlbumInfoWrapper.isSuccess()) {
                    albumAdapter.add(kpAlbumInfoWrapper.getData());
                }
                hideLoding();
            }

            @Override
            public void failure(RetrofitError error) {
                hideLoding();
            }
        });
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoding() {
        progressBar.setVisibility(View.GONE);
    }
}
