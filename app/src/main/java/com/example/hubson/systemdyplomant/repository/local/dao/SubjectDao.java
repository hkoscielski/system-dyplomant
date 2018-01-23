package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.Subject;

import java.util.List;

@Dao
public interface SubjectDao {
    @Query("SELECT * FROM Subjects WHERE id_subject=:idSubject")
    public Subject findSubjectById(final int idSubject);

    @Query("SELECT * FROM Subjects WHERE id_subject=:idSupervisor")
    public List<Subject> findSubjectsForSupervisor(final int idSupervisor);

    @Insert
    public void insert(Subject subject);

    @Update
    public void update(Subject subject);

    @Delete
    public void delete(Subject subject);
}
