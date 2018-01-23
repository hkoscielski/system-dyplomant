package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;

import java.util.List;


@Dao
public interface GraduateDao {
    @Query("SELECT * FROM Graduates WHERE id_graduate=:idGraduate")
    public Graduate findGraduateById(final int idGraduate);

    @Query("SELECT * FROM Graduates WHERE id_subject=:idSubject")
    public List<Graduate> findGraduatesForSubject(final int idSubject);

    @Insert
    public void insert(Graduate graduate);

    @Update
    public void update(Graduate graduate);

    @Delete
    public void delete(Graduate graduate);
}
