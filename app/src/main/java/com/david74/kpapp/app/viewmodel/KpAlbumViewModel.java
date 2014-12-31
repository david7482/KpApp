package com.david74.kpapp.app.viewmodel;

import com.david74.kpapp.api.model.KpAlbumInfo;

import java.util.ArrayList;
import java.util.List;

public class KpAlbumViewModel implements Model {

    KpAlbumInfo albumInfo;

    public KpAlbumViewModel(KpAlbumInfo albumInfo) {
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

    public static List<KpAlbumViewModel> ConvertToModelList(List<KpAlbumInfo> albumInfoList) {
        List<KpAlbumViewModel> list = new ArrayList<KpAlbumViewModel>();
        for (KpAlbumInfo albumInfo : albumInfoList) {
            list.add(new KpAlbumViewModel(albumInfo));
        }
        return list;
    }
}
