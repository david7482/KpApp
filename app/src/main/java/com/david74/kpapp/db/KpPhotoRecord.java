package com.david74.kpapp.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "KpPhotoTable")
public class KpPhotoRecord extends Model {

    @Column(name = "ImageUrl")
    public String imageUrl;

    @Column(name = "ImageUrlHighResolution")
    public String imageUrlHighResolution;

    @Column(name = "Title")
    public String title;

    @Column(name = "Descripion")
    public String descripion;

    @Column(name = "AlbumId")
    public String albumId;

    public KpPhotoRecord() {}
}
