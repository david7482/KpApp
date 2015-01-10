package com.david74.kpapp.api2.model;

public class KpImages {
    String small;
    String small_square;
    String large_square;
    String thumbnail;
    String medium;
    String large;
    String original;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getSmall_square() {
        return small_square;
    }

    public void setSmall_square(String small_square) {
        this.small_square = small_square;
    }

    public String getLarge_square() {
        return large_square;
    }

    public void setLarge_square(String large_square) {
        this.large_square = large_square;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public String toString() {
        return "Images{" +
                "small='" + small + '\'' +
                ", small_square='" + small_square + '\'' +
                ", large_square='" + large_square + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", medium='" + medium + '\'' +
                ", large='" + large + '\'' +
                ", original='" + original + '\'' +
                '}';
    }
}
