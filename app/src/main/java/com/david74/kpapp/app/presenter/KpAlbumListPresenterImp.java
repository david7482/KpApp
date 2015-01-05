package com.david74.kpapp.app.presenter;

import com.david74.kpapp.api.KpApiCaller;
import com.david74.kpapp.api.model.KpAlbumInfoWrapper;
import com.david74.kpapp.app.fragment.KpAlbumControl;
import com.david74.kpapp.app.model.KpAlbumModel;
import com.david74.kpapp.app.model.Model;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class KpAlbumListPresenterImp implements KpAlbumListPresenter {

    private KpAlbumControl control;

    @Override
    public void initialize() {
        control.showLoading();

        KpApiCaller.getApiCaller().getAlbumListAsync(new Callback<KpAlbumInfoWrapper>() {
            @Override
            public void success(KpAlbumInfoWrapper kpAlbumInfoWrapper, Response response) {
                control.hideLoading();

                if (kpAlbumInfoWrapper.isSuccess()) {
                    List<Model> list = KpAlbumModel.ConvertToModelList(kpAlbumInfoWrapper.getData());
                    control.add(list);
                }
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
