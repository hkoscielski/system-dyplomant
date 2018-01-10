package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Supervisors")
public class Supervisor {
    @PrimaryKey
    @ColumnInfo(name = "id_supervisor")
    private int idSupervisor;

    @ColumnInfo(name = "academic_title")
    private String academicTitle;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "surname")
    private String surname;

    @ColumnInfo(name = "id_department")
    private int idDepartment;
}
