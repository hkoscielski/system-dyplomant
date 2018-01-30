package com.example.hubson.systemdyplomant.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.hubson.systemdyplomant.repository.local.dao.GraduateDao;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.repository.remote.Webservice;
import com.example.hubson.systemdyplomant.repository.remote.response_model.ApiResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.GraduateResponse;
import com.example.hubson.systemdyplomant.utils.AppExecutors;

import java.util.List;

public class GraduateRepository {
    private static final Object LOCK = new Object();
    private static GraduateRepository sInstance;

    private final GraduateDao graduateDao;
    private final Webservice webservice;
    private final AppExecutors appExecutors;

    private GraduateRepository(GraduateDao graduateDao, Webservice webservice, AppExecutors appExecutors) {
        this.graduateDao = graduateDao;
        this.webservice = webservice;
        this.appExecutors = appExecutors;
    }

    public synchronized static GraduateRepository getInstance(GraduateDao graduateDao, Webservice webservice, AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new GraduateRepository(graduateDao, webservice, executors);
            }
        }
        return sInstance;
    }

    public LiveData<Resource<List<Graduate>>> loadAllGraduates() {
        return new NetworkBoundResource<List<Graduate>, GraduateResponse>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull GraduateResponse item) {
                graduateDao.insertAll(item.getResults());
                for(Graduate graduate : item.getResults()) {
                    Log.i("saveCallResult", graduate.getSurname());
                }
            }

            @NonNull
            @Override
            protected LiveData<List<Graduate>> loadFromDb() {
                return graduateDao.loadAllGraduates();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Graduate> data) {
                return true;
            }

            @Override
            protected void onFetchFailed() {
                Log.e("loadAllGraduates", "Lipa panie");
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GraduateResponse>> createCall() {
                return webservice.getGraduates();
            }
        }.getAsLiveData();
    }
}
