package com.david74.kpapp.app.model;

import com.david74.kpapp.api.model.KpAlbumInfo;

import java.util.ArrayList;
import java.util.List;

public class KpAlbumModel implements Model {

    KpAlbumInfo albumInfo;

    public KpAlbumModel(KpAlbumInfo albumInfo) {
        this.albumInfo = albumInfo;
    }

    @Override
    public String getImageUrl() {
        return albumInfo.getThumbnails().getSmall();
    }

    @Override
    public String getTitle() {
        return albumInfo.getTitle();
    }

    @Override
    public String getDescription() {
        return albumInfo.getDescription();
    }

    @Override
    public String getId() {
        return albumInfo.getId();
    }

    public static List<Model> ConvertToModelList(List<KpAlbumInfo> albumInfoList) {
        List<Model> list = new ArrayList<Model>();
        for (KpAlbumInfo albumInfo : albumInfoList) {
            list.add(new KpAlbumModel(albumInfo));
        }
        return list;
    }
}
