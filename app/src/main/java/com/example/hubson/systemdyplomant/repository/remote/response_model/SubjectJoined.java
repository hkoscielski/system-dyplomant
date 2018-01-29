package com.example.hubson.systemdyplomant.repository.remote.response_model;

import android.arch.persistence.room.Embedded;

import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;
import com.example.hubson.systemdyplomant.repository.local.entity.Supervisor;
import com.google.gson.annotations.SerializedName;

public class SubjectJoined extends Subject {
    @SerializedName("supervisor")
    @Embedded
    private Supervisor supervisor;

    @SerializedName("subject_status")
    @Embedded
    private SubjectStatus subjectStatus;

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
}
