package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Forms_Of_Studies")
public class FormOfStudies {
    @PrimaryKey
    @ColumnInfo(name = "id_form")
    private int idForm;

    @ColumnInfo(name = "form_name")
    private int formName;
}
