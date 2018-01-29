package com.example.hubson.systemdyplomant.repository.local;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.hubson.systemdyplomant.repository.local.dao.SubjectDao;
import com.example.hubson.systemdyplomant.repository.local.dao.SubjectStatusDao;
import com.example.hubson.systemdyplomant.repository.local.db_helper.SubjectDbHelper;
import com.example.hubson.systemdyplomant.repository.remote.ApiConstants;
import com.example.hubson.systemdyplomant.repository.remote.Webservice;
import com.example.hubson.systemdyplomant.utils.LiveDataCallAdapterFactory;

import org.junit.Before;
import org.junit.runner.RunWith;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(AndroidJUnit4.class)
public class RetrofitTest {
    private Webservice webservice;
    private AppDatabase db;
    private SubjectDao subjectDao;
    private SubjectStatusDao subjectStatusDao;
    private SubjectDbHelper subjectDbHelper;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        webservice = new Retrofit.Builder()
                .baseUrl(ApiConstants.HTTP_GRADUATE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build().create(Webservice.class);
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        subjectDao = db.getSubjectDao();
        subjectStatusDao = db.getSubjectStatusDao();
        subjectDbHelper = new SubjectDbHelper(subjectDao, subjectStatusDao, supervisorDao);
    }
}
