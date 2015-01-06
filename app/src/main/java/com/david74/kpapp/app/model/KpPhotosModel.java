package com.david74.kpapp.app.model;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class KpPhotosModel {

    List<String> photoUrlList;
    int selectedPosition;

    public KpPhotosModel() {
    }

    public KpPhotosModel(int selectedPosition, List<Model> modelList) {

        photoUrlList = new ArrayList<String>();
        for (Model model : modelList) {
            photoUrlList.add(model.getImageUrlHighResolution());
        }

        this.selectedPosition = selectedPosition;
    }

    public KpPhotosModel(List<String> photoUrlList, int selectedPosition) {
        this.photoUrlList = photoUrlList;
        this.selectedPosition = selectedPosition;
    }

    public List<String> getPhotoUrlList() {
        return photoUrlList;
    }

    public void setPhotoUrlList(List<String> photoUrlList) {
        this.photoUrlList = photoUrlList;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
}
