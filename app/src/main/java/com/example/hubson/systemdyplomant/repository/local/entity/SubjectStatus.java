package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Subject_Statuses")
public class SubjectStatus {
    @PrimaryKey
    @ColumnInfo(name = "id_subject_statuses")
    private int idSubjectStatuses;

    @ColumnInfo(name = "status_name")
    private String statusName;
}
