package com.example.hubson.systemdyplomant.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.hubson.systemdyplomant.repository.GraduateRepository;
import com.example.hubson.systemdyplomant.repository.SubjectRepository;

public class SubjectListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final SubjectRepository subjectRepository;
    private final GraduateRepository graduateRepository;

    public SubjectListViewModelFactory(SubjectRepository subjectRepository, GraduateRepository graduateRepository) {
        this.subjectRepository = subjectRepository;
        this.graduateRepository = graduateRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new SubjectListViewModel(subjectRepository, graduateRepository);
    }
}
