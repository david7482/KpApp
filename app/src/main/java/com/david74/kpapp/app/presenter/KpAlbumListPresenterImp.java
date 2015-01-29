package com.david74.kpapp.app.presenter;

import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import com.david74.kpapp.api2.KpApiCaller;
import com.david74.kpapp.api2.model.AlbumDetail;
import com.david74.kpapp.api2.model.AlbumsInfo;
import com.david74.kpapp.app.control.KpAlbumControl;
import com.david74.kpapp.app.model.KpAlbumModel;
import com.david74.kpapp.app.model.KpPhotoModel;
import com.david74.kpapp.app.model.Model;
import com.david74.kpapp.db.KpAlbumRecord;
import com.david74.kpapp.db.KpDBHelper;

import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class KpAlbumListPresenterImp implements KpAlbumListPresenter {

    private KpAlbumControl control;

    @Override
    public void initialize() {
        control.showLoading();

        From from = new Select().from(KpAlbumRecord.class);

        if (from.exists()) {
            List<KpAlbumRecord> list = from.execute();
            if (list != null && !list.isEmpty()) {
                control.hideLoading();

                List<Model> modelList = KpAlbumModel.ConvertRecordToModelList(list);
                control.add(modelList);
                return;
            }
        }

        KpApiCaller.getApiCaller().getKpAlbums(new Callback<AlbumsInfo>() {
            @Override
            public void success(AlbumsInfo albumsInfo, Response response) {
                control.hideLoading();

                List<Model> list = KpAlbumModel.ConvertToModelList(albumsInfo.getFlickralbums().getAlbumInfos().getAlbumInfoList());
                control.add(list);

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
        // Do nothing
    }

    @Override
    public void onViewResume() {
        // Do nothing
    }

    @Override
    public void onViewDestroy() {
        // Do nothing
    }

    @Override
    public void setView(KpAlbumControl view) {
        control = view;
    }
}
