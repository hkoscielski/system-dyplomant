package com.example.hubson.systemdyplomant.view.subjects;

import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoined;

public interface SubjectListCallback {
    void onSubjectClicked(SubjectJoined subjectJoined);
}
