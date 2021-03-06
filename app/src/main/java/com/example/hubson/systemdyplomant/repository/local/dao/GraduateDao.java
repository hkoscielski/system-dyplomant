package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;

import java.util.List;

@Dao
public interface GraduateDao {
    @Query("SELECT * FROM Graduates")
    LiveData<List<Graduate>> loadAllGraduates();

    @Query("SELECT * FROM Graduates WHERE id_graduate=:idGraduate")
    LiveData<Graduate> loadGraduateById(final int idGraduate);

    @Query("SELECT * FROM Graduates WHERE student_no=:studentNo LIMIT 1")
    LiveData<Graduate> loadGraduateByStudentNo(final String studentNo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Graduate> graduates);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Graduate graduate);

    @Update
    void update(Graduate graduate);

    @Delete
    void delete(Graduate graduate);

    @Query("DELETE FROM Graduates")
    void deleteAll();
}
