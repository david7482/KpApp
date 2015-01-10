package com.david74.kpapp.api2.model;

import com.google.gson.annotations.SerializedName;

public class AlbumsInfo {

    @SerializedName("flickr-albums")
    FlickrAlbums flickralbums;

    public FlickrAlbums getFlickralbums() {
        return flickralbums;
    }

    public void setFlickralbums(FlickrAlbums flickralbums) {
        this.flickralbums = flickralbums;
    }
}
