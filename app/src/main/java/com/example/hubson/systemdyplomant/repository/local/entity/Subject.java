package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Subjects", foreignKeys = {
        @ForeignKey(entity = Supervisor.class,
                    parentColumns = "id_supervisor",
                    childColumns = "id_supervisor"),
        @ForeignKey(entity = SubjectStatus.class,
                    parentColumns = "id_subject_status",
                    childColumns = "id_subject_status")
})
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

    public String getSubjectPl() {
        return subjectPl;
    }

    public void setSubjectPl(String subjectPl) {
        this.subjectPl = subjectPl;
    }

    public String getSubjectEn() {
        return subjectEn;
    }

    public void setSubjectEn(String subjectEn) {
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

    public int getIdSubjectStatus() {
        return idSubjectStatus;
    }

    public void setIdSubjectStatus(int idSubjectStatus) {
        this.idSubjectStatus = idSubjectStatus;
    }
}
