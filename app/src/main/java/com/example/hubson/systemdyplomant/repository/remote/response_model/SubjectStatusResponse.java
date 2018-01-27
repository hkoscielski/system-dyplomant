package com.example.hubson.systemdyplomant.repository.remote.response_model;

import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubjectStatusResponse {
    @SerializedName("subject_statuses")
    private List<SubjectStatus> results;

    public List<SubjectStatus> getResults() {
        return results;
    }

    public void setResults(List<SubjectStatus> results) {
        this.results = results;
    }
}
