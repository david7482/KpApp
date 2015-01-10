package com.david74.kpapp.api2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumInfos {

    int totalResults;

    @SerializedName("items")
    List<AlbumInfo> albumInfoList;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<AlbumInfo> getAlbumInfoList() {
        return albumInfoList;
    }

    public void setAlbumInfoList(List<AlbumInfo> albumInfoList) {
        this.albumInfoList = albumInfoList;
    }
}
