package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Departments")
public class Department {
    @SerializedName("id_department")
    @PrimaryKey
    @ColumnInfo(name = "id_department")
    private int idDepartment;

    @SerializedName("department_name")
    @NonNull
    @ColumnInfo(name = "department_name")
    private String departmentName;

    public Department() {}

    public Department(int idDepartment, @NonNull String departmentName) {
        this.idDepartment = idDepartment;
        this.departmentName = departmentName;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    @NonNull
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(@NonNull String departmentName) {
        this.departmentName = departmentName;
    }
}
