package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;

@Dao
public interface SubjectStatusDao {
    @Query("SELECT * FROM Subject_Statuses WHERE status_name=:statusName")
    public SubjectStatus findStatusByName(String statusName);

    @Insert
    public void insert(SubjectStatus subjectStatus);

    @Update
    public void update(SubjectStatus subjectStatus);

    @Delete
    public void delete(SubjectStatus subjectStatus);
}
