package com.example.hubson.systemdyplomant.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.hubson.systemdyplomant.repository.DeclarationRepository;
import com.example.hubson.systemdyplomant.repository.Resource;
import com.example.hubson.systemdyplomant.repository.SubjectRepository;
import com.example.hubson.systemdyplomant.repository.SupervisorRepository;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.local.entity.Supervisor;
import com.example.hubson.systemdyplomant.utils.AbsentLiveData;

import java.util.Objects;

public class DeclarationViewModel extends ViewModel {
    private final MutableLiveData<Integer> idSupervisor = new MutableLiveData<>();
    private final MutableLiveData<Integer> idSubject = new MutableLiveData<>();
    private LiveData<Resource<Supervisor>> supervisor = new MutableLiveData<>();
    private LiveData<Resource<Subject>> subject = new MutableLiveData<>();

    public DeclarationViewModel(SupervisorRepository supervisorRepository, DeclarationRepository declarationRepository,
                                SubjectRepository subjectRepository) {
        supervisor = Transformations.switchMap(idSupervisor, idSupervisor -> {
            if(idSupervisor == null) {
                return AbsentLiveData.create();
            } else {
                return supervisorRepository.loadSupervisor(idSupervisor);
            }
        });
        subject = Transformations.switchMap(idSubject, idSubject -> {
            if(idSubject == null) {
                return AbsentLiveData.create();
            } else {
                return subjectRepository.loadSubject(idSubject);
            }
        });
    }

    public void setIdSupervisor(Integer idSupervisor) {
        if (Objects.equals(this.idSupervisor.getValue(), idSupervisor)) {
            return;
        }
        this.idSupervisor.setValue(idSupervisor);
    }

    public void setIdSubject(Integer idSubject) {
        if (Objects.equals(this.idSubject.getValue(), idSubject)) {
            return;
        }
        this.idSubject.setValue(idSubject);
    }

    public LiveData<Resource<Supervisor>> getSupervisor() {
        return supervisor;
    }

    public LiveData<Resource<Subject>> getSubject() {
        return subject;
    }
}
