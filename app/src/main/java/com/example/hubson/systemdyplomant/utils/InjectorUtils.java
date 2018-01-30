package com.example.hubson.systemdyplomant.utils;

import android.content.Context;

import com.example.hubson.systemdyplomant.repository.GraduateRepository;
import com.example.hubson.systemdyplomant.repository.SubjectRepository;
import com.example.hubson.systemdyplomant.repository.local.AppDatabase;
import com.example.hubson.systemdyplomant.repository.remote.Webservice;
import com.example.hubson.systemdyplomant.repository.remote.WebserviceImpl;
import com.example.hubson.systemdyplomant.viewmodel.SubjectListViewModelFactory;

public class InjectorUtils {
    public static SubjectRepository provideSubjectRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        Webservice webservice = WebserviceImpl.getInstance();
        return SubjectRepository.getInstance(database.getSubjectDao(), database.getSubjectStatusDao(),
                database.getSupervisorDao(), webservice, executors);
    }

    public static GraduateRepository provideGraduateRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        Webservice webservice = WebserviceImpl.getInstance();
        return GraduateRepository.getInstance(database.getGraduateDao(), webservice, executors);
    }

    public static SubjectListViewModelFactory provideSubjectListActivityViewModelFactory(Context context) {
        SubjectRepository subjectRepository = provideSubjectRepository(context.getApplicationContext());
        GraduateRepository graduateRepository = provideGraduateRepository(context.getApplicationContext());
        return new SubjectListViewModelFactory(subjectRepository, graduateRepository);
    }
}
