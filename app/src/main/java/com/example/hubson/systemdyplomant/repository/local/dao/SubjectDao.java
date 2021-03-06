package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoined;

import java.util.List;

@Dao
public interface SubjectDao {
    @Query("SELECT * FROM Subjects")
    LiveData<List<Subject>> loadAllSubjects();

    @Query("SELECT S.*, SS.*, SV.* " +
            "FROM Subjects S " +
            "JOIN Subject_Statuses SS ON S.subject_status_id = SS.id_subject_status " +
            "JOIN Supervisors SV ON S.supervisor_id = SV.id_supervisor")
    LiveData<List<SubjectJoined>> loadAllSubjectsJoined();

    @Query("SELECT * FROM Subjects WHERE id_subject=:idSubject")
    LiveData<Subject> loadSubjectById(final int idSubject);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Subject> subjects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Subject subject);

    @Update
    void update(Subject subject);

    @Delete
    void delete(Subject subject);

    @Query("DELETE FROM Subjects")
    void deleteAll();
}
