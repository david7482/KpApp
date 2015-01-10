package com.david74.kpapp.api2;

import com.david74.kpapp.api2.model.AlbumsInfo;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

public class KpApiCaller {
    private final static String baseUrl = "http://tonyq.org/kp-wild";

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
        @GET("/static_data.json")
        void getKpAlbums(Callback<AlbumsInfo> callback);
    }
}
