package com.example.hubson.systemdyplomant.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.hubson.systemdyplomant.repository.local.dao.SupervisorDao;
import com.example.hubson.systemdyplomant.repository.local.entity.Supervisor;
import com.example.hubson.systemdyplomant.repository.remote.Webservice;
import com.example.hubson.systemdyplomant.repository.remote.response_model.ApiResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SupervisorResponse;
import com.example.hubson.systemdyplomant.utils.AppExecutors;

public class SupervisorRepository {
    private static final Object LOCK = new Object();
    private static SupervisorRepository sInstance;

    private final SupervisorDao supervisorDao;
    private final Webservice webservice;
    private final AppExecutors appExecutors;

    private SupervisorRepository(SupervisorDao supervisorDao, Webservice webservice, AppExecutors appExecutors) {
        this.supervisorDao = supervisorDao;
        this.webservice = webservice;
        this.appExecutors = appExecutors;
    }

    public static SupervisorRepository getInstance(SupervisorDao supervisorDao, Webservice webservice,
                                                             AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if(sInstance == null) {
                    sInstance = new SupervisorRepository(supervisorDao, webservice, executors);
                }
            }
        }
        return sInstance;
    }

    public LiveData<Resource<Supervisor>> loadSupervisor(int idSupervisor) {
        return new NetworkBoundResource<Supervisor, SupervisorResponse>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull SupervisorResponse item) {
                supervisorDao.insertAll(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<Supervisor> loadFromDb() {
                return supervisorDao.loadSupervisorById(idSupervisor);
            }

            @Override
            protected boolean shouldFetch(@Nullable Supervisor data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<SupervisorResponse>> createCall() {
                return webservice.getSupervisor(idSupervisor);
            }
        }.getAsLiveData();
    }
}
