package com.david74.kpapp.app.presenter;

public interface Presenter<T> {
    void initialize();

    void onViewCreate();

    void onViewResume();

    void onViewDestroy();

    void setView(T view);
}
