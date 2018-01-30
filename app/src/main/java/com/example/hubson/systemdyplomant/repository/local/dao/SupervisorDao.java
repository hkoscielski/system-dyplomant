package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.Supervisor;

import java.util.List;

@Dao
public interface SupervisorDao {
    @Query("SELECT * FROM Supervisors WHERE id_supervisor=:idSupervisor")
    LiveData<Supervisor> loadSupervisorById(final int idSupervisor);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Supervisor> supervisors);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Supervisor supervisor);

    @Update
    void update(Supervisor supervisor);

    @Delete
    void delete(Supervisor supervisor);
}
