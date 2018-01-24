package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Supervisors", foreignKeys = @ForeignKey(entity = Department.class,
                                                             parentColumns = "id_department",
                                                             childColumns = "id_department"))
public class Supervisor {
    @SerializedName("id_supervisor")
    @PrimaryKey
    @ColumnInfo(name = "id_supervisor")
    private int idSupervisor;

    @SerializedName("academic_title")
    @NonNull
    @ColumnInfo(name = "academic_title")
    private String academicTitle;

    @SerializedName("name")
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("surname")
    @NonNull
    @ColumnInfo(name = "surname")
    private String surname;

    @SerializedName("id_department")
    @ColumnInfo(name = "id_department")
    private Integer idDepartment;

    public Supervisor() {}

    public Supervisor(int idSupervisor, @NonNull String academicTitle, @NonNull String name, @NonNull String surname, Integer idDepartment) {
        this.idSupervisor = idSupervisor;
        this.academicTitle = academicTitle;
        this.name = name;
        this.surname = surname;
        this.idDepartment = idDepartment;
    }

    public int getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(int idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    @NonNull
    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(@NonNull String academicTitle) {
        this.academicTitle = academicTitle;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }
}
