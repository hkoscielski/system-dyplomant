package com.example.hubson.systemdyplomant.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.hubson.systemdyplomant.repository.Resource;
import com.example.hubson.systemdyplomant.repository.SubjectRepository;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;

import java.util.List;

public class SubjectListViewModel extends AndroidViewModel {
    private final SubjectRepository subjectRepository;
    private final LiveData<Resource<List<Subject>>> subjects;

    public SubjectListViewModel(@NonNull Application application) {
        super(application);
        subjectRepository = new SubjectRepository(application);
        subjects = subjectRepository.loadAllSubjects();
    }

    public LiveData<Resource<List<Subject>>> getSubjects() {
        return subjects;
    }
}
