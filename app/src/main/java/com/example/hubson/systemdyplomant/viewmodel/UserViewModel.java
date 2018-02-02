package com.example.hubson.systemdyplomant.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.hubson.systemdyplomant.repository.GraduateRepository;
import com.example.hubson.systemdyplomant.repository.Resource;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.utils.AbsentLiveData;

import java.util.Objects;

public class UserViewModel extends ViewModel{
    private final MutableLiveData<String> studentNo = new MutableLiveData<>();

    private final LiveData<Resource<Graduate>> graduate;

    public UserViewModel(GraduateRepository graduateRepository) {
        graduate = Transformations.switchMap(studentNo, studentNo -> {
            if(studentNo == null) {
                return AbsentLiveData.create();
            } else {
                return graduateRepository.loadGraduate(studentNo);
            }
        });
    }

    public void setStudentNo(String studentNo) {
        if (Objects.equals(this.studentNo.getValue(), studentNo)) {
            return;
        }
        this.studentNo.setValue(studentNo);
    }

    public LiveData<Resource<Graduate>> getGraduate() {
        return graduate;
    }
}
