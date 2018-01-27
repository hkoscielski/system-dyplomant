package com.example.hubson.systemdyplomant.repository.local.db_helper;

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
        subjectsLiveData = Transformations.switchMap(subjectsLiveData, inputSubjects -> {
            MediatorLiveData<List<Subject>> subjectsMediatorLiveData = new MediatorLiveData<>();
            for (Subject subject : inputSubjects) {
                subjectsMediatorLiveData.addSource(subjectStatusDao.loadStatusById(subject.getIdSubjectStatus()), subjectStatus -> {
                    subject.setSubjectStatus(subjectStatus);
                    Log.i("Subject", subject.getSubjectPl());
                    Log.i("Status", subjectStatus != null ? subjectStatus.getStatusName() : "null");
                    subjectsMediatorLiveData.postValue(inputSubjects);
                });
            }
            return subjectsMediatorLiveData;
        });
        return subjectsLiveData;
    }
}
