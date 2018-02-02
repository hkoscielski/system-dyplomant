package com.example.hubson.systemdyplomant.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.hubson.systemdyplomant.repository.local.dao.FormOfStudiesDao;
import com.example.hubson.systemdyplomant.repository.local.dao.GraduateDao;
import com.example.hubson.systemdyplomant.repository.local.entity.FormOfStudies;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.repository.remote.Webservice;
import com.example.hubson.systemdyplomant.repository.remote.response_model.ApiResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.GraduateResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.PostResponse;
import com.example.hubson.systemdyplomant.utils.AppExecutors;

import java.util.List;

public class GraduateRepository {
    private static final Object LOCK = new Object();
    private static GraduateRepository sInstance;

    private final GraduateDao graduateDao;
    private final FormOfStudiesDao formOfStudiesDao;
    private final Webservice webservice;
    private final AppExecutors appExecutors;

    private GraduateRepository(GraduateDao graduateDao, FormOfStudiesDao formOfStudiesDao,
                               Webservice webservice, AppExecutors appExecutors) {
        this.graduateDao = graduateDao;
        this.formOfStudiesDao = formOfStudiesDao;
        this.webservice = webservice;
        this.appExecutors = appExecutors;
    }

    public synchronized static GraduateRepository getInstance(GraduateDao graduateDao, FormOfStudiesDao formOfStudiesDao,
                                                              Webservice webservice, AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new GraduateRepository(graduateDao, formOfStudiesDao, webservice, executors);
            }
        }
        return sInstance;
    }

    public LiveData<Resource<List<Graduate>>> loadAllGraduates() {
        return new NetworkBoundResource<List<Graduate>, GraduateResponse>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull GraduateResponse item) {
                Log.e("graduate", String.valueOf(item.getResults() == null));
                graduateDao.insertAll(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<Graduate>> loadFromDb() {
                return graduateDao.loadAllGraduates();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GraduateResponse>> createCall() {
                return webservice.getGraduates();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<Graduate>> loadGraduate(int idGraduate) {
        return new NetworkBoundResource<Graduate, Graduate>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull Graduate item) {
                graduateDao.insert(item);
            }

            @NonNull
            @Override
            protected LiveData<Graduate> loadFromDb() {
                return graduateDao.loadGraduateById(idGraduate);
            }

            @Override
            protected boolean shouldFetch(@Nullable Graduate data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Graduate>> createCall() {
                return webservice.getGraduate(idGraduate);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<Graduate>> loadGraduate(String studentNo) {
        return new NetworkBoundResource<Graduate, Graduate>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull Graduate item) {
                graduateDao.insert(item);
            }

            @NonNull
            @Override
            protected LiveData<Graduate> loadFromDb() {
                return graduateDao.loadGraduateByStudentNo(studentNo);
            }

            @Override
            protected boolean shouldFetch(@Nullable Graduate data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Graduate>> createCall() {
                return webservice.getGraduate(studentNo);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<FormOfStudies>> loadFormOfStudies(int idForm) {
        return new NetworkBoundResource<FormOfStudies, FormOfStudies>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull FormOfStudies item) {
                formOfStudiesDao.insert(item);
            }

            @NonNull
            @Override
            protected LiveData<FormOfStudies> loadFromDb() {
                return formOfStudiesDao.loadFormById(idForm);
            }

            @Override
            protected boolean shouldFetch(@Nullable FormOfStudies data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<FormOfStudies>> createCall() {
                return webservice.getFormOfStudies(idForm);
            }
        }.getAsLiveData();
    }

    public LiveData<ApiResponse<PostResponse>> updateGraduate(Graduate graduate) {
        appExecutors.diskIO().execute(() -> graduateDao.update(graduate));
        return webservice.updateGraduate(graduate);
    }
}
