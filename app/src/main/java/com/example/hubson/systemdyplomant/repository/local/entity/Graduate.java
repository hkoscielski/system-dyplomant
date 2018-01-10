package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Graduates")
public class Graduate {
    @PrimaryKey
    @ColumnInfo(name = "id_graduate")
    private int idGraduate;

    @ColumnInfo(name = "id_subject")
    private int idSubject;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "surname")
    private String surname;

    @ColumnInfo(name = "student_no")
    private String studentNo;

    @ColumnInfo(name = "speciality")
    private String speciality;

    @ColumnInfo(name = "id_form")
    private int idForm;

    @ColumnInfo(name = "year_of_studies")
    private int yearOfStudies;
}
