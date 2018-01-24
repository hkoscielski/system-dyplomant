package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Subject_Statuses")
public class SubjectStatus {
    @SerializedName("id_subject_status")
    @PrimaryKey
    @ColumnInfo(name = "id_subject_status")
    private int idSubjectStatus;

    @SerializedName("status_name")
    @NonNull
    @ColumnInfo(name = "status_name")
    private String statusName;

    public SubjectStatus() {}

    public SubjectStatus(int idSubjectStatus, @NonNull String statusName) {
        this.idSubjectStatus = idSubjectStatus;
        this.statusName = statusName;
    }

    public int getIdSubjectStatus() {
        return idSubjectStatus;
    }

    public void setIdSubjectStatus(int idSubjectStatus) {
        this.idSubjectStatus = idSubjectStatus;
    }

    @NonNull
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(@NonNull String statusName) {
        this.statusName = statusName;
    }
}
