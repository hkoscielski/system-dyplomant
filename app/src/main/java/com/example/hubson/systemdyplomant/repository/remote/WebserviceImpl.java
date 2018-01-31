package com.example.hubson.systemdyplomant.repository.remote;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebserviceImpl {
    private static final Object LOCK = new Object();
    private static Webservice sInstance;

    private WebserviceImpl() {}

    public synchronized static Webservice getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new Retrofit.Builder()
                    .baseUrl(ApiConstants.HTTP_GRADUATE_API)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .build().create(Webservice.class);
            }
        }
        return sInstance;
    }
}
