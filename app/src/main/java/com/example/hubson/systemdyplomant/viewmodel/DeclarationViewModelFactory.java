package com.example.hubson.systemdyplomant.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.hubson.systemdyplomant.repository.DeclarationRepository;
import com.example.hubson.systemdyplomant.repository.SubjectRepository;
import com.example.hubson.systemdyplomant.repository.SupervisorRepository;

public class DeclarationViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final SupervisorRepository supervisorRepository;
    private final DeclarationRepository declarationRepository;
    private final SubjectRepository subjectRepository;

    public DeclarationViewModelFactory(SupervisorRepository supervisorRepository, DeclarationRepository declarationRepository,
                                       SubjectRepository subjectRepository) {
        this.supervisorRepository = supervisorRepository;
        this.declarationRepository = declarationRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DeclarationViewModel(supervisorRepository, declarationRepository, subjectRepository);
    }
}
