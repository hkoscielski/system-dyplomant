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
    public Graduate findGraduateById(final int idGraduate);

    @Query("SELECT * FROM Graduates WHERE subject_id=:idSubject")
    public List<Graduate> findGraduatesForSubject(final int idSubject);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Graduate> graduates);

    @Insert
    public void insert(Graduate graduate);

    @Update
    public void update(Graduate graduate);

    @Delete
    public void delete(Graduate graduate);
}
