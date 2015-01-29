package com.david74.kpapp.app.model;


import com.david74.kpapp.api2.model.AlbumDetail;
import com.david74.kpapp.db.KpAlbumRecord;
import com.david74.kpapp.db.KpPhotoRecord;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class KpPhotoModel implements Model {

    String imageUrl;
    String imageUrlHighResolution;
    String title;
    String descripion;
    String id;

    public KpPhotoModel() {

    }

    public KpPhotoModel(AlbumDetail.Photo photo) {
        imageUrl = photo.getImages().getSmall();
        imageUrlHighResolution = photo.getImages().getLarge();
        title = photo.getTitle();
        descripion = "";
        id = photo.getId();
    }

    public KpPhotoModel(KpPhotoRecord record) {
        imageUrl = record.imageUrl;
        imageUrlHighResolution = record.imageUrlHighResolution;
        title = record.title;
        descripion = record.descripion;
        id = record.albumId;
    }

    public KpPhotoModel(String imageUrl, String imageUrlHighResolution, String title, String descripion, String id) {
        this.imageUrl = imageUrl;
        this.imageUrlHighResolution = imageUrlHighResolution;
        this.title = title;
        this.descripion = descripion;
        this.id = id;
    }

    public void saveToDB(String albumId) {
        KpPhotoRecord record = new KpPhotoRecord();
        record.albumId = albumId;
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

    public static List<Model> ConvertToModelList(List<AlbumDetail.Photo> photoList) {
        List<Model> list = new ArrayList<Model>();
        for (AlbumDetail.Photo photo : photoList) {
            list.add(new KpPhotoModel(photo));
        }
        return list;
    }

    public static List<Model> ConvertRecordToModelList(List<KpPhotoRecord> recordList) {
        List<Model> list = new ArrayList<Model>();
        for (KpPhotoRecord record : recordList) {
            list.add(new KpPhotoModel(record));
        }
        return list;
    }
}
