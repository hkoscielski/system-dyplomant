package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Subjects")
public class Subject {
    @PrimaryKey
    @ColumnInfo(name = "id_subject")
    private int idSubject;

    @ColumnInfo(name = "id_supervisor")
    private int idSupervisor;

    @ColumnInfo(name = "subject_pl")
    private String subjectPl;

    @ColumnInfo(name = "subject_en")
    private String subjectEn;

    @ColumnInfo(name = "taken_up")
    private int takenUp;

    @ColumnInfo(name = "limit")
    private int limit;

    @ColumnInfo(name = "id_subject_status")
    private int idSubjectStatus;
}
