package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.Department;

@Dao
public interface DepartmentDao {
    @Query("SELECT * FROM Departments WHERE id_department=:idDepartment")
    public Department findDepartmentById(final int idDepartment);

    @Insert
    public void insert(Department department);

    @Update
    public void update(Department department);

    @Delete
    public void delete(Department department);
}
