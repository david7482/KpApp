package com.david74.kpapp.app.control;

import com.david74.kpapp.app.model.Model;

import java.util.List;

public interface KpAlbumDetailControl {
    void showLoading();

    void hideLoading();

    void add(Model model);

    void add(List<Model> modelList);
}
