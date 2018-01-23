package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Graduates", foreignKeys = {
        @ForeignKey(entity = Subject.class,
                    parentColumns = "id_subject",
                    childColumns = "id_subject"),
        @ForeignKey(entity = FormOfStudies.class,
                    parentColumns = "id_form",
                    childColumns = "id_form")
})
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

    public int getIdGraduate() {
        return idGraduate;
    }

    public void setIdGraduate(int idGraduate) {
        this.idGraduate = idGraduate;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    public int getYearOfStudies() {
        return yearOfStudies;
    }

    public void setYearOfStudies(int yearOfStudies) {
        this.yearOfStudies = yearOfStudies;
    }
}
