package com.example.hubson.systemdyplomant.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.hubson.systemdyplomant.repository.Resource;
import com.example.hubson.systemdyplomant.repository.SubjectRepository;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoined;

import java.util.List;

public class SubjectListViewModel extends AndroidViewModel {
    private final SubjectRepository subjectRepository;
//    private final LiveData<Resource<List<Subject>>> subjects;
//    private final LiveData<Resource<List<SubjectStatus>>> subjectsStatuses;
    private final LiveData<Resource<List<Graduate>>> graduates;
    private final LiveData<Resource<List<SubjectJoined>>> subjectsJoined;

    public SubjectListViewModel(@NonNull Application application) {
        super(application);
        subjectRepository = new SubjectRepository(application);
//        subjectsStatuses = subjectRepository.loadAllSubjectStatuses();
//        subjects = subjectRepository.loadAllSubjects();
        graduates = subjectRepository.loadAllGraduates();
        subjectsJoined = subjectRepository.loadAllSubjectJoined();


    }

    public LiveData<Resource<List<SubjectJoined>>> getSubjectsJoined() {
        return subjectsJoined;
    }

    public LiveData<Resource<List<Graduate>>> getGraduates() {
        return graduates;
    }

//        public LiveData<Resource<List<Subject>>> getSubjects() {
//        return subjects;
//    }

//    public LiveData<Resource<List<SubjectStatus>>> getSubjectsStatuses() {
//        return subjectsStatuses;
//    }
}
