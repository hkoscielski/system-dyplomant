package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.Supervisor;

@Dao
public interface SupervisorDao {
    @Query("SELECT * FROM Supervisor WHERE id_supervisor=:idSupervisor")
    public Supervisor findSupervisorById(final int idSupervisor);

    @Insert
    public void insert(Supervisor supervisor);

    @Update
    public void update(Supervisor supervisor);

    @Delete
    public void delete(Supervisor supervisor);
}
