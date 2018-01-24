package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Forms_Of_Studies")
public class FormOfStudies {
    @SerializedName("id_form")
    @PrimaryKey
    @ColumnInfo(name = "id_form")
    private int idForm;

    @SerializedName("form_name")
    @NonNull
    @ColumnInfo(name = "form_name")
    private String formName;

    public FormOfStudies() {}

    public FormOfStudies(int idForm, @NonNull String formName) {
        this.idForm = idForm;
        this.formName = formName;
    }

    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    @NonNull
    public String getFormName() {
        return formName;
    }

    public void setFormName(@NonNull String formName) {
        this.formName = formName;
    }
}
