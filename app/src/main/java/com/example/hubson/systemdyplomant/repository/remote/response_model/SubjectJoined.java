package com.example.hubson.systemdyplomant.repository.remote.response_model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;
import com.example.hubson.systemdyplomant.repository.local.entity.Supervisor;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubjectJoined extends Subject {
    @SerializedName("supervisor")
    @Embedded
    private Supervisor supervisor;

    @SerializedName("subject_status")
    @Embedded
    private SubjectStatus subjectStatus;

    @Relation(parentColumn = "id_subject", entityColumn = "subject_id", entity = Graduate.class)
    private List<Graduate> graduates;

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public SubjectStatus getSubjectStatus() {
        return subjectStatus;
    }

    public void setSubjectStatus(SubjectStatus subjectStatus) {
        this.subjectStatus = subjectStatus;
    }

    public List<Graduate> getGraduates() {
        return graduates;
    }

    public void setGraduates(List<Graduate> graduates) {
        this.graduates = graduates;
    }
}
