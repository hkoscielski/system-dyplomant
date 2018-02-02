package com.example.hubson.systemdyplomant.viewmodel;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.hubson.systemdyplomant.repository.GraduateRepository;

public class UserViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private final GraduateRepository graduateRepository;

    public UserViewModelFactory(GraduateRepository graduateRepository) {
        this.graduateRepository = graduateRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new UserViewModel(graduateRepository);
    }
}
