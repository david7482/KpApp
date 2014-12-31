package com.david74.kpapp.api.model;

import java.util.List;

public class KpAlbumInfoWrapper {
    boolean isSuccess;
    int errorCode;
    String errorMessage;
    KpPageInfo pageInfo;
    List<KpAlbumInfo> data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public KpPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(KpPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<KpAlbumInfo> getData() {
        return data;
    }

    public void setData(List<KpAlbumInfo> data) {
        this.data = data;
    }
}
