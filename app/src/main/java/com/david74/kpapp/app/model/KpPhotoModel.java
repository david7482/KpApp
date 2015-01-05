package com.david74.kpapp.app.model;

import com.david74.kpapp.api.model.KpPhotoInfo;

import java.util.ArrayList;
import java.util.List;

public class KpPhotoModel implements Model {

    KpPhotoInfo.Photo photo;

    public KpPhotoModel(KpPhotoInfo.Photo photo) {
        this.photo = photo;
    }

    @Override
    public String getImageUrl() {
        return photo.getImages().getSmall();
    }

    @Override
    public String getTitle() {
        return photo.getTitle();
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getId() {
        return photo.getId();
    }

    public static List<Model> ConvertToModelList(List<KpPhotoInfo.Photo> photoList) {
        List<Model> list = new ArrayList<Model>();
        for (KpPhotoInfo.Photo photo : photoList) {
            list.add(new KpPhotoModel(photo));
        }
        return list;
    }
}
