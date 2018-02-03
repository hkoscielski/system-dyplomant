package com.example.hubson.systemdyplomant.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.hubson.systemdyplomant.repository.local.dao.DeclarationDao;
import com.example.hubson.systemdyplomant.repository.local.dao.DeclarationStatusDao;
import com.example.hubson.systemdyplomant.repository.local.entity.Declaration;
import com.example.hubson.systemdyplomant.repository.local.entity.DeclarationStatus;
import com.example.hubson.systemdyplomant.repository.remote.Webservice;
import com.example.hubson.systemdyplomant.repository.remote.response_model.ApiResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.DeclarationStatusResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.PostResponse;
import com.example.hubson.systemdyplomant.utils.AppExecutors;

import java.util.List;

public class DeclarationRepository {
    private static final Object LOCK = new Object();
    private static DeclarationRepository sInstance;

    private final DeclarationDao declarationDao;
    private final DeclarationStatusDao declarationStatusDao;
    private final Webservice webservice;
    private final AppExecutors appExecutors;

    private DeclarationRepository(DeclarationDao declarationDao, DeclarationStatusDao declarationStatusDao,
                                 Webservice webservice, AppExecutors appExecutors) {
        this.declarationDao = declarationDao;
        this.declarationStatusDao = declarationStatusDao;
        this.webservice = webservice;
        this.appExecutors = appExecutors;
    }

    public static DeclarationRepository getInstance(DeclarationDao declarationDao, DeclarationStatusDao declarationStatusDao,
                                                                Webservice webservice, AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if(sInstance == null) {
                    sInstance = new DeclarationRepository(declarationDao, declarationStatusDao, webservice, executors);
                }
            }
        }
        return sInstance;
    }

    public LiveData<Resource<List<DeclarationStatus>>> loadAllDeclarationStatuses() {
        return new NetworkBoundResource<List<DeclarationStatus>, DeclarationStatusResponse>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull DeclarationStatusResponse item) {
                declarationStatusDao.insertAll(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<DeclarationStatus>> loadFromDb() {
                return declarationStatusDao.loadAllStatuses();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<DeclarationStatusResponse>> createCall() {
                return webservice.getDeclarationStatuses();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<DeclarationStatus>> loadDeclarationStatus(String statusName) {
        return new NetworkBoundResource<DeclarationStatus, DeclarationStatus>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull DeclarationStatus item) {
                declarationStatusDao.insert(item);
            }

            @NonNull
            @Override
            protected LiveData<DeclarationStatus> loadFromDb() {
                return declarationStatusDao.loadStatusByName(statusName);
            }

            @Override
            protected boolean shouldFetch(@Nullable DeclarationStatus data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<DeclarationStatus>> createCall() {
                return webservice.getDeclarationStatus(statusName);
            }
        }.getAsLiveData();
    }

    public LiveData<ApiResponse<PostResponse>> saveDeclaration(Declaration declaration) {
        return webservice.createDeclaration(declaration);
    }
}
