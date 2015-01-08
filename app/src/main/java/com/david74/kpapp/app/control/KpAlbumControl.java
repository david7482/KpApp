package com.david74.kpapp.app.control;

import com.david74.kpapp.app.model.Model;

import java.util.List;

public interface KpAlbumControl {

    void showLoading();

    void hideLoading();

    void add(Model model);

    void add(List<Model> modelList);
}
