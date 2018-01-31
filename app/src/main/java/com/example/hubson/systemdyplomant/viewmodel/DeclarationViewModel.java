package com.example.hubson.systemdyplomant.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.hubson.systemdyplomant.repository.DeclarationRepository;
import com.example.hubson.systemdyplomant.repository.GraduateRepository;
import com.example.hubson.systemdyplomant.repository.Resource;
import com.example.hubson.systemdyplomant.repository.SubjectRepository;
import com.example.hubson.systemdyplomant.repository.SupervisorRepository;
import com.example.hubson.systemdyplomant.repository.local.entity.Declaration;
import com.example.hubson.systemdyplomant.repository.local.entity.DeclarationStatus;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.local.entity.Supervisor;
import com.example.hubson.systemdyplomant.repository.remote.response_model.ApiResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.CreateResponse;
import com.example.hubson.systemdyplomant.utils.AbsentLiveData;

import java.util.Objects;

public class DeclarationViewModel extends ViewModel {
    private final MutableLiveData<Integer> idSupervisor = new MutableLiveData<>();
    private final MutableLiveData<Integer> idSubject = new MutableLiveData<>();
    private final MutableLiveData<String> declarationStatusName = new MutableLiveData<>();
    private final MutableLiveData<Integer> idGraduate = new MutableLiveData<>();

    private LiveData<Resource<Supervisor>> supervisor = new MutableLiveData<>();
    private LiveData<Resource<Subject>> subject = new MutableLiveData<>();
    private LiveData<Resource<DeclarationStatus>> declarationStatus = new MutableLiveData<>();
    private LiveData<Resource<Graduate>> graduate = new MutableLiveData<>();

    private DeclarationRepository declarationRepository;

    public DeclarationViewModel(SupervisorRepository supervisorRepository, DeclarationRepository declarationRepository,
                                SubjectRepository subjectRepository, GraduateRepository graduateRepository) {
        this.declarationRepository = declarationRepository;

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
        declarationStatus = Transformations.switchMap(declarationStatusName, statusName -> {
            if(statusName == null) {
                return AbsentLiveData.create();
            } else {
                return declarationRepository.loadDeclarationStatus(statusName);
            }
        });
        graduate = Transformations.switchMap(idGraduate, idGraduate -> {
            if(idGraduate == null) {
                return AbsentLiveData.create();
            } else {
                return graduateRepository.loadGraduate(idGraduate);
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

    public void setDeclarationStatusName(String statusName) {
        if (Objects.equals(this.declarationStatusName.getValue(), statusName)) {
            return;
        }
        this.declarationStatusName.setValue(statusName);
    }

    public void setIdGraduate(Integer idGraduate) {
        if (Objects.equals(this.idGraduate.getValue(), idGraduate)) {
            return;
        }
        this.idGraduate.setValue(idGraduate);
    }

    public LiveData<Resource<Supervisor>> getSupervisor() {
        return supervisor;
    }

    public LiveData<Resource<Subject>> getSubject() {
        return subject;
    }

    public LiveData<Resource<DeclarationStatus>> getDeclarationStatus() {
        return declarationStatus;
    }

    public LiveData<Resource<Graduate>> getGraduate() {
        return graduate;
    }

    public LiveData<ApiResponse<CreateResponse>> createDeclaration(Declaration declaration) {
        return declarationRepository.saveDeclaration(declaration);
    }
}
