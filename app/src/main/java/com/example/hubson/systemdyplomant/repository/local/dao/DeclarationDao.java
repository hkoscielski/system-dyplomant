package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.Declaration;

@Dao
public interface DeclarationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Declaration declaration);

    @Update
    void update(Declaration declaration);

    @Delete
    void delete(Declaration declaration);

    @Query("DELETE FROM Declarations")
    void deleteAll();
}
