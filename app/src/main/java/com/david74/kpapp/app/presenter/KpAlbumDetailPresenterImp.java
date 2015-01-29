package com.david74.kpapp.app.presenter;

import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import com.david74.kpapp.api2.KpApiCaller;
import com.david74.kpapp.api2.model.AlbumDetail;
import com.david74.kpapp.api2.model.AlbumsInfo;
import com.david74.kpapp.api2.model.FlickrAlbums;
import com.david74.kpapp.app.control.KpAlbumDetailControl;
import com.david74.kpapp.app.model.KpAlbumModel;
import com.david74.kpapp.app.model.KpPhotoModel;
import com.david74.kpapp.app.model.Model;
import com.david74.kpapp.db.KpAlbumRecord;
import com.david74.kpapp.db.KpDBHelper;
import com.david74.kpapp.db.KpPhotoRecord;

import java.util.HashMap;
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

        From from = new Select().from(KpPhotoRecord.class);

        if (from.exists()) {
            List<KpPhotoRecord> list = from.where("AlbumId = ?", albumId).execute();
            if (list != null && !list.isEmpty()) {
                control.hideLoading();

                List<Model> modelList = KpPhotoModel.ConvertRecordToModelList(list);
                control.add(modelList);
                return;
            }
        }

        KpApiCaller.getApiCaller().getKpAlbums(new Callback<AlbumsInfo>() {
            @Override
            public void success(AlbumsInfo albumsInfo, Response response) {
                HashMap<String, AlbumDetail> map = albumsInfo.getFlickralbums().getAlbumDetails();
                AlbumDetail albumDetail = map.get(albumId);
                control.add(KpPhotoModel.ConvertToModelList(albumDetail.getPhotos()));
                control.hideLoading();

                KpDBHelper.saveAlbumsInfoToDB(albumsInfo);
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
