package com.david74.kpapp.api2.model;

public class AlbumInfo {
    String id;
    int photos;
    int videos;
    String title;
    String description;
    String date_create;
    String date_update;
    String link;
    KpImages thumbnails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhotos(int photos) {
        this.photos = photos;
    }

    public int getVideos() {
        return videos;
    }

    public void setVideos(int videos) {
        this.videos = videos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getDate_update() {
        return date_update;
    }

    public void setDate_update(String date_update) {
        this.date_update = date_update;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public KpImages getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(KpImages thumbnails) {
        this.thumbnails = thumbnails;
    }

    @Override
    public String toString() {
        return "KpAlbumInfo{" +
                "id='" + id + '\'' +
                ", photos=" + photos +
                ", videos=" + videos +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date_create='" + date_create + '\'' +
                ", date_update='" + date_update + '\'' +
                ", link='" + link + '\'' +
                ", thumbnails=" + thumbnails +
                '}';
    }
}
