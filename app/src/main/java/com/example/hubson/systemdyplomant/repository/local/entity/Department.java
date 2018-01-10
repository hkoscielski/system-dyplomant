package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Departments")
public class Department {
    @PrimaryKey
    @ColumnInfo(name = "id_department")
    private int idDepartment;

    @ColumnInfo(name = "department_name")
    private int departmentName;
}
