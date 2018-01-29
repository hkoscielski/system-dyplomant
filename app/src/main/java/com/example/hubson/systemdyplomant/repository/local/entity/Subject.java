package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Subjects"//, foreignKeys = {
//        @ForeignKey(entity = Supervisor.class,
//                    parentColumns = "id_supervisor",
//                    childColumns = "id_supervisor"),
//        @ForeignKey(entity = SubjectStatus.class,
//                    parentColumns = "id_subject_status",
//                    childColumns = "id_subject_status")
//})
)
public class Subject {
    @SerializedName("id_subject")
    @PrimaryKey
    @ColumnInfo(name = "id_subject")
    private int idSubject;

    @SerializedName("id_supervisor")
    @ColumnInfo(name = "supervisor_id")
    private int idSupervisor;

    @SerializedName("subject_pl")
    @NonNull
    @ColumnInfo(name = "subject_pl")
    private String subjectPl;

    @SerializedName("subject_en")
    @NonNull
    @ColumnInfo(name = "subject_en")
    private String subjectEn;

    @SerializedName("taken_up")
    @IntRange(from = 0)
    @ColumnInfo(name = "taken_up")
    private int takenUp;

    @SerializedName("graduates_limit")
    @IntRange(from = 0)
    @ColumnInfo(name = "limit")
    private int limit;

    @SerializedName("id_subject_status")
    @NonNull
    @ColumnInfo(name = "subject_status_id")
    private Integer idSubjectStatus;

    public Subject() {}

    public Subject(int idSubject, int idSupervisor, @NonNull String subjectPl, @NonNull String subjectEn, int takenUp, int limit, @NonNull Integer idSubjectStatus) {
        this.idSubject = idSubject;
        this.idSupervisor = idSupervisor;
        this.subjectPl = subjectPl;
        this.subjectEn = subjectEn;
        this.takenUp = takenUp;
        this.limit = limit;
        this.idSubjectStatus = idSubjectStatus;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public int getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(int idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    @NonNull
    public String getSubjectPl() {
        return subjectPl;
    }

    public void setSubjectPl(@NonNull String subjectPl) {
        this.subjectPl = subjectPl;
    }

    @NonNull
    public String getSubjectEn() {
        return subjectEn;
    }

    public void setSubjectEn(@NonNull String subjectEn) {
        this.subjectEn = subjectEn;
    }

    public int getTakenUp() {
        return takenUp;
    }

    public void setTakenUp(int takenUp) {
        this.takenUp = takenUp;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @NonNull
    public Integer getIdSubjectStatus() {
        return idSubjectStatus;
    }

    public void setIdSubjectStatus(@NonNull Integer idSubjectStatus) {
        this.idSubjectStatus = idSubjectStatus;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "idSubject=" + idSubject +
                ", idSupervisor=" + idSupervisor +
                ", subjectPl='" + subjectPl + '\'' +
                ", subjectEn='" + subjectEn + '\'' +
                ", takenUp=" + takenUp +
                ", limit=" + limit +
                ", idSubjectStatus=" + idSubjectStatus +
                '}' + "\n\n";
    }
}
