package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Graduates", foreignKeys = {
        @ForeignKey(entity = Subject.class,
                parentColumns = "id_subject",
                childColumns = "id_subject"),
        @ForeignKey(entity = FormOfStudies.class,
                parentColumns = "id_form",
                childColumns = "id_form")
})
public class Graduate {
    @SerializedName("id_graduate")
    @PrimaryKey
    @ColumnInfo(name = "id_graduate")
    private int idGraduate;

    @SerializedName("id_subject")
    @Nullable
    @ColumnInfo(name = "id_subject")
    private Integer idSubject;

    @SerializedName("name")
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("surname")
    @NonNull
    @ColumnInfo(name = "surname")
    private String surname;

    @SerializedName("student_no")
    @NonNull
    @ColumnInfo(name = "student_no")
    private String studentNo;

    @SerializedName("speciality")
    @Nullable
    @ColumnInfo(name = "speciality")
    private String speciality;

    @SerializedName("id_form")
    @NonNull
    @ColumnInfo(name = "id_form")
    private Integer idForm;

    @SerializedName("year_of_studies")
    @NonNull
    @ColumnInfo(name = "year_of_studies")
    private Integer yearOfStudies;

    public Graduate() {};

    public Graduate(int idGraduate, @Nullable  Integer idSubject, @NonNull String name, @NonNull String surname, @NonNull String studentNo, @Nullable String speciality, @NonNull Integer idForm, @NonNull Integer yearOfStudies) {
        this.idGraduate = idGraduate;
        this.idSubject = idSubject;
        this.name = name;
        this.surname = surname;
        this.studentNo = studentNo;
        this.speciality = speciality;
        this.idForm = idForm;
        this.yearOfStudies = yearOfStudies;
    }

    public int getIdGraduate() {
        return idGraduate;
    }

    public void setIdGraduate(int idGraduate) {
        this.idGraduate = idGraduate;
    }

    public @Nullable Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(@Nullable Integer idSubject) {
        this.idSubject = idSubject;
    }

    public @NonNull String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public @NonNull String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }

    public @NonNull String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(@NonNull String studentNo) {
        this.studentNo = studentNo;
    }

    public @Nullable String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(@Nullable String speciality) {
        this.speciality = speciality;
    }

    public @NonNull Integer getIdForm() {
        return idForm;
    }

    public void setIdForm(@NonNull Integer idForm) {
        this.idForm = idForm;
    }

    public @NonNull Integer getYearOfStudies() {
        return yearOfStudies;
    }

    public void setYearOfStudies(@NonNull Integer yearOfStudies) {
        this.yearOfStudies = yearOfStudies;
    }
}
