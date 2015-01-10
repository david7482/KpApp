package com.david74.kpapp.api2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumDetail {

    Set set;
    int totalResults;

    @SerializedName("items")
    List<Photo> photoList;

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Photo> getPhotos() {
        return photoList;
    }

    public void setPhotos(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public static class Set {
        String id;
        String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class Location {
        int latitude;
        int longitude;

        public int getLatitude() {
            return latitude;
        }

        public void setLatitude(int latitude) {
            this.latitude = latitude;
        }

        public int getLongitude() {
            return longitude;
        }

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }
    }

    public static class Photo {
        String id;
        KpImages images;
        String date_upload;
        String date_taken;
        Location location;
        String title;
        int isprimary;
        String link;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public KpImages getImages() {
            return images;
        }

        public void setImages(KpImages images) {
            this.images = images;
        }

        public String getDate_upload() {
            return date_upload;
        }

        public void setDate_upload(String date_upload) {
            this.date_upload = date_upload;
        }

        public String getDate_taken() {
            return date_taken;
        }

        public void setDate_taken(String date_taken) {
            this.date_taken = date_taken;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIsprimary() {
            return isprimary;
        }

        public void setIsprimary(int isprimary) {
            this.isprimary = isprimary;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
