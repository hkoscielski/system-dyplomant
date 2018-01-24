package com.example.hubson.systemdyplomant.repository.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.hubson.systemdyplomant.repository.local.dao.FormOfStudiesDao;
import com.example.hubson.systemdyplomant.repository.local.dao.GraduateDao;
import com.example.hubson.systemdyplomant.repository.local.entity.*;

@Database(entities = {Declaration.class, DeclarationStatus.class, Department.class, FormOfStudies.class,
        Graduate.class, Subject.class, SubjectStatus.class, Supervisor.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GraduateDao getGraduateDao();
    public abstract FormOfStudiesDao getFormOfStudiesDao();
}
