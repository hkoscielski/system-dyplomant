package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.DeclarationStatus;

@Dao
public interface DeclarationStatusDao {
    @Query("SELECT * FROM Declaration_Statuses WHERE status_name=:statusName")
    public DeclarationStatus findStatusByName(String statusName);

    @Insert
    public void insert(DeclarationStatus declarationStatus);

    @Update
    public void update(DeclarationStatus declarationStatus);

    @Delete
    public void delete(DeclarationStatus declarationStatus);
}
