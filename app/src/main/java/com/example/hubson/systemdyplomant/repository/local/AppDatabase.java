package com.example.hubson.systemdyplomant.repository.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.hubson.systemdyplomant.repository.local.dao.DeclarationDao;
import com.example.hubson.systemdyplomant.repository.local.dao.DeclarationStatusDao;
import com.example.hubson.systemdyplomant.repository.local.dao.FormOfStudiesDao;
import com.example.hubson.systemdyplomant.repository.local.dao.GraduateDao;
import com.example.hubson.systemdyplomant.repository.local.dao.SubjectDao;
import com.example.hubson.systemdyplomant.repository.local.dao.SubjectStatusDao;
import com.example.hubson.systemdyplomant.repository.local.dao.SupervisorDao;
import com.example.hubson.systemdyplomant.repository.local.entity.*;

@Database(entities = {Declaration.class, DeclarationStatus.class, Department.class, FormOfStudies.class,
        Graduate.class, Subject.class, SubjectStatus.class, Supervisor.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static AppDatabase dbInstance;

    public abstract GraduateDao getGraduateDao();
    public abstract FormOfStudiesDao getFormOfStudiesDao();
    public abstract SubjectDao getSubjectDao();
    public abstract SubjectStatusDao getSubjectStatusDao();
    public abstract SupervisorDao getSupervisorDao();
    public abstract DeclarationDao getDeclarationDao();
    public abstract DeclarationStatusDao getDeclarationStatusDao();

    public static AppDatabase getInstance(Context context) {
        if (dbInstance == null) {
            synchronized (LOCK) {
                dbInstance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).build();
            }
        }
        return dbInstance;
    }
}
