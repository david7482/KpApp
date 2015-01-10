package com.david74.kpapp.app.presenter;

import com.david74.kpapp.api2.KpApiCaller;
import com.david74.kpapp.api2.model.AlbumDetail;
import com.david74.kpapp.api2.model.AlbumsInfo;
import com.david74.kpapp.api2.model.FlickrAlbums;
import com.david74.kpapp.app.control.KpAlbumDetailControl;
import com.david74.kpapp.app.model.KpPhotoModel;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class KpAlbumDetailPresenterImp implements KpAlbumDetailPresenter {

    private KpAlbumDetailControl control;
    private String albumId;

    public KpAlbumDetailPresenterImp(String albumId) {
        this.albumId = albumId;
    }

    @Override
    public void initialize() {

        control.showLoading();

        KpApiCaller.getApiCaller().getKpAlbums(new Callback<AlbumsInfo>() {
            @Override
            public void success(AlbumsInfo albumsInfo, Response response) {
                HashMap<String, AlbumDetail> map = albumsInfo.getFlickralbums().getAlbumDetails();
                AlbumDetail albumDetail = map.get(albumId);
                control.add(KpPhotoModel.ConvertToModelList(albumDetail.getPhotos()));
                control.hideLoading();
            }

            @Override
            public void failure(RetrofitError error) {
                control.hideLoading();
            }
        });
    }

    @Override
    public void onViewCreate() {

    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void setView(KpAlbumDetailControl view) {
        control = view;
    }
}
