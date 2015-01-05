package com.david74.kpapp.api.model;

public class KpPhotoInfoWrapper {
    boolean isSuccess;
    int errorCode;
    String errorMessage;
    KpPageInfo pageInfo;
    KpPhotoInfo data;

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

    public KpPhotoInfo getData() {
        return data;
    }

    public void setData(KpPhotoInfo data) {
        this.data = data;
    }
}
