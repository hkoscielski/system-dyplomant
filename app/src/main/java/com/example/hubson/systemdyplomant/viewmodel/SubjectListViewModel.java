package com.example.hubson.systemdyplomant.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.hubson.systemdyplomant.repository.Resource;
import com.example.hubson.systemdyplomant.repository.SubjectRepository;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoined;

import java.util.List;

public class SubjectListViewModel extends ViewModel {
    private final SubjectRepository subjectRepository;

    private final LiveData<Resource<List<Graduate>>> graduates;
    private final LiveData<Resource<List<SubjectJoined>>> subjectsJoined;

    public SubjectListViewModel(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;

        graduates = subjectRepository.loadAllGraduates();
        subjectsJoined = subjectRepository.loadAllSubjectJoined();
    }

    public LiveData<Resource<List<SubjectJoined>>> getSubjectsJoined() {
        return subjectsJoined;
    }

    public LiveData<Resource<List<Graduate>>> getGraduates() {
        return graduates;
    }
}
