package com.example.hubson.systemdyplomant.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.hubson.systemdyplomant.repository.DeclarationRepository;
import com.example.hubson.systemdyplomant.repository.GraduateRepository;
import com.example.hubson.systemdyplomant.repository.SubjectRepository;
import com.example.hubson.systemdyplomant.repository.SupervisorRepository;

public class DeclarationViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final SupervisorRepository supervisorRepository;
    private final DeclarationRepository declarationRepository;
    private final SubjectRepository subjectRepository;
    private final GraduateRepository graduateRepository;

    public DeclarationViewModelFactory(SupervisorRepository supervisorRepository, DeclarationRepository declarationRepository,
                                       SubjectRepository subjectRepository, GraduateRepository graduateRepository) {
        this.supervisorRepository = supervisorRepository;
        this.declarationRepository = declarationRepository;
        this.subjectRepository = subjectRepository;
        this.graduateRepository = graduateRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DeclarationViewModel(supervisorRepository, declarationRepository, subjectRepository, graduateRepository);
    }
}
