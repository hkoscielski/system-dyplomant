package com.example.hubson.systemdyplomant.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.hubson.systemdyplomant.repository.local.dao.SupervisorDao;
import com.example.hubson.systemdyplomant.repository.remote.response_model.ApiResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoined;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoinedResponse;
import com.example.hubson.systemdyplomant.utils.AppExecutors;
import com.example.hubson.systemdyplomant.repository.local.dao.SubjectDao;
import com.example.hubson.systemdyplomant.repository.local.dao.SubjectStatusDao;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;
import com.example.hubson.systemdyplomant.repository.remote.Webservice;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectStatusResponse;

import java.util.List;

public class SubjectRepository {
    private static final Object LOCK = new Object();
    private static SubjectRepository sInstance;

    private final SubjectDao subjectDao;
    private final SubjectStatusDao subjectStatusDao;
    private final SupervisorDao supervisorDao;
    private final Webservice webservice;
    private final AppExecutors appExecutors;

    private SubjectRepository(SubjectDao subjectDao, SubjectStatusDao subjectStatusDao,
                              SupervisorDao supervisorDao, Webservice webservice, AppExecutors appExecutors) {
        this.subjectDao = subjectDao;
        this.subjectStatusDao = subjectStatusDao;
        this.supervisorDao = supervisorDao;
        this.webservice = webservice;
        this.appExecutors = appExecutors;
    }

    public synchronized static SubjectRepository getInstance(SubjectDao subjectDao, SubjectStatusDao subjectStatusDao,
                                                             SupervisorDao supervisorDao, Webservice webservice,
                                                             AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SubjectRepository(subjectDao, subjectStatusDao,
                        supervisorDao, webservice, executors);
            }
        }
        return sInstance;
    }

    public LiveData<Resource<List<SubjectJoined>>> loadAllSubjectJoined() {
        return new NetworkBoundResource<List<SubjectJoined>, SubjectJoinedResponse>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull SubjectJoinedResponse item) {
                List<SubjectJoined> subjectsJoined = item.getResults();
                for(SubjectJoined subjectJoined : subjectsJoined) {
                    subjectDao.insert(subjectJoined);
                    subjectStatusDao.insert(subjectJoined.getSubjectStatus());
                    supervisorDao.insert(subjectJoined.getSupervisor());
                }
            }

            @NonNull
            @Override
            protected LiveData<List<SubjectJoined>> loadFromDb() {
                return subjectDao.loadAllSubjectsJoined();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<SubjectJoinedResponse>> createCall() {
                return webservice.getSubjectsJoined();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<Subject>> loadSubject(int idSubject) {
        return new NetworkBoundResource<Subject, Subject>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull Subject item) {
                subjectDao.insert(item);
            }

            @NonNull
            @Override
            protected LiveData<Subject> loadFromDb() {
                return subjectDao.loadSubjectById(idSubject);
            }

            @Override
            protected boolean shouldFetch(@Nullable Subject data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Subject>> createCall() {
                return webservice.getSubject(idSubject);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<SubjectStatus>>> loadAllSubjectStatuses() {
        return new NetworkBoundResource<List<SubjectStatus>, SubjectStatusResponse>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull SubjectStatusResponse item) {
                subjectStatusDao.deleteAll();
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