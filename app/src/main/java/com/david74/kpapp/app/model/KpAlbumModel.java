package com.david74.kpapp.app.model;

import com.david74.kpapp.api.model.KpAlbumInfo;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class KpAlbumModel implements Model {

    String imageUrl;
    String title;
    String descripion;
    String id;

    public KpAlbumModel() {

    }

    public KpAlbumModel(KpAlbumInfo albumInfo) {
        imageUrl = albumInfo.getThumbnails().getSmall();
        title = albumInfo.getTitle();
        descripion = albumInfo.getDescription();
        id = albumInfo.getId();
    }

    public KpAlbumModel(String imageUrl, String title, String descripion, String id) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.descripion = descripion;
        this.id = id;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return descripion;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static List<Model> ConvertToModelList(List<KpAlbumInfo> albumInfoList) {
        List<Model> list = new ArrayList<Model>();
        for (KpAlbumInfo albumInfo : albumInfoList) {
            list.add(new KpAlbumModel(albumInfo));
        }
        return list;
    }
}
