package com.example.hubson.systemdyplomant.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.hubson.systemdyplomant.repository.remote.response_model.ApiResponse;
import com.example.hubson.systemdyplomant.utils.AppExecutors;
import com.example.hubson.systemdyplomant.repository.local.AppDatabase;
import com.example.hubson.systemdyplomant.repository.local.dao.SubjectDao;
import com.example.hubson.systemdyplomant.repository.local.dao.SubjectStatusDao;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;
import com.example.hubson.systemdyplomant.repository.remote.ApiConstants;
import com.example.hubson.systemdyplomant.repository.remote.Webservice;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectStatusResponse;
import com.example.hubson.systemdyplomant.utils.LiveDataCallAdapterFactory;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubjectRepository {
    private final SubjectDao subjectDao;
    private final SubjectStatusDao subjectStatusDao;
    private final Webservice webservice;
    private final AppDatabase db;
    //private final SubjectDbHelper subjectDbHelper;
    private final AppExecutors appExecutors;

//    public SubjectRepository(SubjectDao subjectDao, Webservice webservice) {
//        this.subjectDao = subjectDao;
//        this.webservice = webservice;
//    }
    public SubjectRepository(Context context) {
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        subjectDao = db.getSubjectDao();
        subjectStatusDao = db.getSubjectStatusDao();
        webservice = new Retrofit.Builder()
                .baseUrl(ApiConstants.HTTP_GRADUATE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build().create(Webservice.class);
        //loadAllSubjectStatuses();
        //subjectDbHelper = new SubjectDbHelper(subjectDao, subjectStatusDao);
        appExecutors = new AppExecutors();
    }

    public LiveData<Resource<List<Subject>>> loadAllSubjects() {
        return new NetworkBoundResource<List<Subject>, SubjectResponse>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull SubjectResponse item) {
                subjectDao.insertAll(item.getResults());
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Subject> data) {
                return data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<Subject>> loadFromDb() {
                return subjectDao.loadAllSubjects();
            }

            @Override
            protected void onFetchFailed() {
                Log.e("loadAllSubjects", "Lipa panie");
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<SubjectResponse>> createCall() {
                return webservice.getSubjects();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<SubjectStatus>>> loadAllSubjectStatuses() {
        return new NetworkBoundResource<List<SubjectStatus>, SubjectStatusResponse>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull SubjectStatusResponse item) {
                subjectStatusDao.insertAll(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<SubjectStatus>> loadFromDb() {
                return subjectStatusDao.loadAllStatuses();
            }

            @Override
            protected void onFetchFailed() {
                Log.e("loadAllSubjectStatuses", "Lipa panie");
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<SubjectStatusResponse>> createCall() {
                return webservice.getSubjectStatuses();
            }
        }.getAsLiveData();
    }
}
