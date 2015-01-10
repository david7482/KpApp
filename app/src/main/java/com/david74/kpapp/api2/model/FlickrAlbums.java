package com.david74.kpapp.api2.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class FlickrAlbums {

    @SerializedName("list")
    AlbumInfos albumInfos;

    @SerializedName("items")
    HashMap<String, AlbumDetail> albumDetails;

    public AlbumInfos getAlbumInfos() {
        return albumInfos;
    }

    public void setAlbumInfos(AlbumInfos albumInfos) {
        this.albumInfos = albumInfos;
    }

    public HashMap<String, AlbumDetail> getAlbumDetails() {
        return albumDetails;
    }

    public void setAlbumDetails(HashMap<String, AlbumDetail> albumDetails) {
        this.albumDetails = albumDetails;
    }
}
