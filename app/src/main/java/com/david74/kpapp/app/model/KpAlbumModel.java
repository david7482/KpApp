package com.david74.kpapp.app.model;

import com.david74.kpapp.api2.model.AlbumInfo;
import com.david74.kpapp.db.KpAlbumRecord;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class KpAlbumModel implements Model {

    String imageUrl;
    String imageUrlHighResolution;
    String title;
    String descripion;
    String id;

    public KpAlbumModel() {

    }

    public KpAlbumModel(AlbumInfo albumInfo) {
        imageUrl = albumInfo.getThumbnails().getSmall();
        imageUrlHighResolution = albumInfo.getThumbnails().getLarge();
        title = albumInfo.getTitle();
        descripion = albumInfo.getDescription();
        id = albumInfo.getId();
    }

    public KpAlbumModel(KpAlbumRecord record) {
        imageUrl = record.imageUrl;
        imageUrlHighResolution = record.imageUrlHighResolution;
        title = record.title;
        descripion = record.descripion;
        id = record.albumId;
    }

    public KpAlbumModel(String imageUrl, String imageUrlHighResolution, String title, String descripion, String id) {
        this.imageUrl = imageUrl;
        this.imageUrlHighResolution = imageUrlHighResolution;
        this.title = title;
        this.descripion = descripion;
        this.id = id;
    }

    public void saveToDB() {
        KpAlbumRecord record = new KpAlbumRecord();
        record.albumId = id;
        record.descripion = descripion;
        record.imageUrl = imageUrl;
        record.imageUrlHighResolution = imageUrlHighResolution;
        record.title = title;
        record.save();
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

    @Override
    public String getImageUrlHighResolution() {
        return imageUrlHighResolution;
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

    public void setImageUrlHighResolution(String imageUrlHighResolution) {
        this.imageUrlHighResolution = imageUrlHighResolution;
    }

    public static List<Model> ConvertToModelList(List<AlbumInfo> albumInfoList) {
        List<Model> list = new ArrayList<Model>();
        for (AlbumInfo albumInfo : albumInfoList) {
            list.add(new KpAlbumModel(albumInfo));
        }
        return list;
    }

    public static List<Model> ConvertRecordToModelList(List<KpAlbumRecord> recordList) {
        List<Model> list = new ArrayList<Model>();
        for (KpAlbumRecord record : recordList) {
            list.add(new KpAlbumModel(record));
        }
        return list;
    }
}
