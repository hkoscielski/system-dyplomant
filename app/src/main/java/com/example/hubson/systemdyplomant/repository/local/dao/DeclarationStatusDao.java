package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.DeclarationStatus;

import java.util.List;

@Dao
public interface DeclarationStatusDao {

    @Query("SELECT * FROM Declaration_Statuses")
    LiveData<List<DeclarationStatus>> loadAllStatuses();

    @Query("SELECT * FROM Declaration_Statuses WHERE status_name=:statusName")
    LiveData<DeclarationStatus> loadStatusByName(String statusName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DeclarationStatus> declarationStatuses);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DeclarationStatus declarationStatus);

    @Update
    void update(DeclarationStatus declarationStatus);

    @Delete
    void delete(DeclarationStatus declarationStatus);

    @Query("DELETE FROM Declaration_Statuses")
    void deleteAll();
}
