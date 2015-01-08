package com.david74.kpapp.app.presenter;

import com.david74.kpapp.api.KpApiCaller;
import com.david74.kpapp.api.model.KpPhotoInfo;
import com.david74.kpapp.api.model.KpPhotoInfoWrapper;
import com.david74.kpapp.app.control.KpAlbumDetailControl;
import com.david74.kpapp.app.model.KpPhotoModel;

import java.util.List;

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

        KpApiCaller.getApiCaller().getAlbumDetailAsync(albumId, new Callback<KpPhotoInfoWrapper>() {
            @Override
            public void success(KpPhotoInfoWrapper kpPhotoInfoWrapper, Response response) {
                if (kpPhotoInfoWrapper.isSuccess()) {
                    List<KpPhotoInfo.Photo> list = kpPhotoInfoWrapper.getData().getPhotos();
                    control.add(KpPhotoModel.ConvertToModelList(list));
                }

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
