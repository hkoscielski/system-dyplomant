package com.example.hubson.systemdyplomant.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.hubson.systemdyplomant.repository.Resource;
import com.example.hubson.systemdyplomant.repository.SubjectRepository;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;

import java.util.List;

public class SubjectListViewModel extends AndroidViewModel {
    private final LiveData<Resource<List<Subject>>> subjects;

    public SubjectListViewModel(@NonNull Application application) {
        super(application);
        subjects = new SubjectRepository(application).loadAllSubjects();
    }

    public LiveData<Resource<List<Subject>>> getSubjects() {
        return subjects;
    }
}
