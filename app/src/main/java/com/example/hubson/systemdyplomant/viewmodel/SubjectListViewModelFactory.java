package com.example.hubson.systemdyplomant.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.hubson.systemdyplomant.repository.SubjectRepository;

public class SubjectListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final SubjectRepository repository;

    public SubjectListViewModelFactory(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new SubjectListViewModel(repository);
    }
}
