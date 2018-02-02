package com.example.hubson.systemdyplomant.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.hubson.systemdyplomant.repository.GraduateRepository;
import com.example.hubson.systemdyplomant.repository.Resource;
import com.example.hubson.systemdyplomant.repository.SubjectRepository;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoined;
import com.example.hubson.systemdyplomant.utils.AbsentLiveData;

import java.util.List;
import java.util.Objects;

public class SubjectListViewModel extends ViewModel {
    private final MutableLiveData<Integer> idGraduate = new MutableLiveData<>();
    private LiveData<Resource<Graduate>> graduate = new MutableLiveData<>();

    private final LiveData<Resource<List<Graduate>>> graduates;
    private final LiveData<Resource<List<SubjectJoined>>> subjectsJoined;

    public SubjectListViewModel(SubjectRepository subjectRepository, GraduateRepository graduateRepository) {
        graduates = graduateRepository.loadAllGraduates();
        subjectsJoined = subjectRepository.loadAllSubjectJoined();
        graduate = Transformations.switchMap(idGraduate, idGraduate -> {
            if(idGraduate == null) {
                return AbsentLiveData.create();
            } else {
                return graduateRepository.loadGraduate(idGraduate);
            }
        });
    }

    public void setIdGraduate(Integer idGraduate) {
        if (Objects.equals(this.idGraduate.getValue(), idGraduate)) {
            return;
        }
        this.idGraduate.setValue(idGraduate);
    }

    public LiveData<Resource<List<SubjectJoined>>> getSubjectsJoined() {
        return subjectsJoined;
    }

    public LiveData<Resource<List<Graduate>>> getGraduates() {
        return graduates;
    }

    public LiveData<Resource<Graduate>> getGraduate() {
        return graduate;
    }
}
