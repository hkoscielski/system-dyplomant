package com.example.hubson.systemdyplomant.repository.remote.response_model;

import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;

public class SubjectWithStatusResponse {
    private Subject subject;
    private SubjectStatus subjectStatus;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SubjectStatus getSubjectStatus() {
        return subjectStatus;
    }

    public void setSubjectStatus(SubjectStatus subjectStatus) {
        this.subjectStatus = subjectStatus;
    }
}
