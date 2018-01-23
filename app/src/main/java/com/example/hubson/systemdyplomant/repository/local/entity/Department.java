package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import com.example.hubson.systemdyplomant.repository.local.converters.DateConverters;

@Entity(tableName = "Departments")
public class Department {
    @PrimaryKey
    @ColumnInfo(name = "id_department")
    private int idDepartment;

    @ColumnInfo(name = "department_name")
    private int departmentName;

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public int getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(int departmentName) {
        this.departmentName = departmentName;
    }
}
