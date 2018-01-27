package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.Subject;

import java.util.List;

@Dao
public interface SubjectDao {
    @Query("SELECT * FROM Subjects")
    LiveData<List<Subject>> loadAllSubjects();

    @Query("SELECT * FROM Subjects WHERE id_subject=:idSubject")
    LiveData<Subject> loadSubjectById(final int idSubject);

    @Query("SELECT * FROM Subjects WHERE id_subject=:idSupervisor")
    LiveData<List<Subject>> findSubjectsForSupervisor(final int idSupervisor);

    @Insert
    void insertAll(List<Subject> subjects);

    @Insert
    void insert(Subject subject);

    @Update
    void update(Subject subject);

    @Delete
    void delete(Subject subject);
}
