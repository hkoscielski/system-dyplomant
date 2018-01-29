package com.example.hubson.systemdyplomant.repository.local.db_helper;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.hubson.systemdyplomant.repository.local.dao.SubjectDao;
import com.example.hubson.systemdyplomant.repository.local.dao.SubjectStatusDao;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;

import java.util.List;

public class SubjectDbHelper {
    private SubjectDao subjectDao;
    private SubjectStatusDao subjectStatusDao;

    public SubjectDbHelper(SubjectDao subjectDao, SubjectStatusDao subjectStatusDao) {
        this.subjectDao = subjectDao;
        this.subjectStatusDao = subjectStatusDao;
    }

    public LiveData<List<Subject>> loadAllSubjects() {
        LiveData<List<Subject>> subjectsLiveData = subjectDao.loadAllSubjects();
        subjectsLiveData = Transformations.switchMap(subjectsLiveData, new Function<List<Subject>, LiveData<List<Subject>>>() {
            @Override
            public LiveData<List<Subject>> apply(List<Subject> input) {
                MediatorLiveData<List<Subject>> subjectsMediatorLiveData = new MediatorLiveData<>();
                for(Subject subject : input) {
                    subjectsMediatorLiveData.addSource(subjectStatusDao.loadStatusById(subject.getIdSubjectStatus()), new Observer<SubjectStatus>() {
                        @Override
                        public void onChanged(@Nullable SubjectStatus subjectStatus) {
                            subject.setSubjectStatus(subjectStatus);
                            subjectsMediatorLiveData.postValue(input);
                        }
                    });
                }
                return subjectsMediatorLiveData;
            }
        });
//        subjectsLiveData = Transformations.map(subjectsLiveData, inputSubjects -> {
//            for(Subject subject : inputSubjects) {
//                subject.setSubjectStatus(subjectStatusDao.loadStatusById(subject.getIdSubjectStatus()).getValue());
//            }
//            return inputSubjects;
//        });
        return subjectsLiveData;
    }
}
