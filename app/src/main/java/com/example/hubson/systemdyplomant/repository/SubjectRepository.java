package com.example.hubson.systemdyplomant.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.hubson.systemdyplomant.repository.local.AppDatabase;
import com.example.hubson.systemdyplomant.repository.local.dao.SubjectDao;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.remote.ApiConstants;
import com.example.hubson.systemdyplomant.repository.remote.Webservice;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubjectRepository {
    private final SubjectDao subjectDao;
    private final Webservice webservice;
    private final AppDatabase db;

//    public SubjectRepository(SubjectDao subjectDao, Webservice webservice) {
//        this.subjectDao = subjectDao;
//        this.webservice = webservice;
//    }
    public SubjectRepository(Context context) {
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        subjectDao = db.getSubjectDao();
        webservice = new Retrofit.Builder()
                .baseUrl(ApiConstants.HTTP_GRADUATE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Webservice.class);
    }

    public LiveData<Resource<List<Subject>>> loadAllSubjects() {
        return new NetworkBoundResource<List<Subject>, SubjectResponse>() {

            @Override
            protected void saveCallResult(@NonNull SubjectResponse item) {
                subjectDao.insertAll(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<Subject>> loadFromDb() {
                return subjectDao.loadAllSubjects();
            }

            @NonNull
            @Override
            protected Call<SubjectResponse> createCall() {
                return webservice.getSubjects();
            }
        }.getAsLiveData();
    }
}
