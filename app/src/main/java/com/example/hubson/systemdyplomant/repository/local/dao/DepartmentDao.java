package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.Department;

@Dao
public interface DepartmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Department department);

    @Update
    void update(Department department);

    @Delete
    void delete(Department department);

    @Query("DELETE FROM Departments")
    void deleteAll();
}
