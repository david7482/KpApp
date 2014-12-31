package com.david74.kpapp.api;

import com.david74.kpapp.api.model.KpAlbumInfoWrapper;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

public class KpApiCaller {
    private final static String baseUrl = "http://api.kptaipei.tw/v1/";
    private final static String accessToken = "kp53f767e35c9e03.05422610";

    private static ApiCallerInterface apiCaller;

    public static ApiCallerInterface getApiCaller() {
        if (apiCaller == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(baseUrl)
                    //.setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
            apiCaller = restAdapter.create(ApiCallerInterface.class);
        }

        return apiCaller;
    }

    public interface ApiCallerInterface {
        @GET("/albums/?accessToken=" + accessToken)
        void getAlbumListAsync(Callback<KpAlbumInfoWrapper> callback);
    }
}
