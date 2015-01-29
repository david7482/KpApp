package com.david74.kpapp.db;

import com.david74.kpapp.api2.model.AlbumDetail;
import com.david74.kpapp.api2.model.AlbumsInfo;
import com.david74.kpapp.app.model.KpAlbumModel;
import com.david74.kpapp.app.model.KpPhotoModel;
import com.david74.kpapp.app.model.Model;

import java.util.HashMap;
import java.util.List;

public class KpDBHelper {

    public static void saveAlbumsInfoToDB(final AlbumsInfo albumsInfo) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Save KpAlbumRecord
                List<Model> list = KpAlbumModel.ConvertToModelList(albumsInfo.getFlickralbums().getAlbumInfos().getAlbumInfoList());
                for (Model model : list) {
                    ((KpAlbumModel) model).saveToDB();
                }

                // Save all photo to DB
                HashMap<String, AlbumDetail> map = albumsInfo.getFlickralbums().getAlbumDetails();
                for (AlbumDetail detail : map.values()) {
                    list = KpPhotoModel.ConvertToModelList(detail.getPhotos());
                    for (Model model : list) {
                        ((KpPhotoModel) model).saveToDB(detail.getSet().getId());
                    }
                }
            }
        }).start();
    }
}
