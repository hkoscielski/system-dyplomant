package com.example.hubson.systemdyplomant.repository.local.db_helper;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.example.hubson.systemdyplomant.repository.local.dao.SubjectDao;
import com.example.hubson.systemdyplomant.repository.local.dao.SubjectStatusDao;
import com.example.hubson.systemdyplomant.repository.local.dao.SupervisorDao;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;

import java.util.List;

public class SubjectDbHelper {
    private SubjectDao subjectDao;
    private SubjectStatusDao subjectStatusDao;
    private SupervisorDao supervisorDao;

    public SubjectDbHelper(SubjectDao subjectDao, SubjectStatusDao subjectStatusDao, SupervisorDao supervisorDao) {
        this.subjectDao = subjectDao;
        this.subjectStatusDao = subjectStatusDao;
        this.supervisorDao = supervisorDao;
    }

    public LiveData<List<Subject>> loadAllSubjects() {
        LiveData<List<Subject>> subjectsLiveData = subjectDao.loadAllSubjects();
//        subjectsLiveData = Transformations.switchMap(subjectsLiveData, new Function<List<Subject>, LiveData<List<Subject>>>() {
//            @Override
//            public LiveData<List<Subject>> apply(List<Subject> input) {
//                MediatorLiveData<List<Subject>> subjectsMediatorLiveData = new MediatorLiveData<>();
//                for(Subject subject : input) {
//                    subjectsMediatorLiveData.addSource(subjectStatusDao.loadStatusById(subject.getIdSubjectStatus()), new Observer<SubjectStatus>() {
//                        @Override
//                        public void onChanged(@Nullable SubjectStatus subjectStatus) {
//                            subject.setSubjectStatus(subjectStatus);
//                            subjectsMediatorLiveData.postValue(input);
//                        }
//                    });
//                }
//                return subjectsMediatorLiveData;
//            }
//        });
//        subjectsLiveData = Transformations.map(subjectsLiveData, inputSubjects -> {
//            for(Subject subject : inputSubjects) {
//                subject.setSubjectStatus(subjectStatusDao.loadStatusById(subject.getIdSubjectStatus()));
//                subject.setSupervisor(supervisorDao.loadSupervisorById(subject.getIdSupervisor()));
//            }
//            return inputSubjects;
//        });
        return subjectsLiveData;
    }
}
