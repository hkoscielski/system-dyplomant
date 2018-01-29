package com.example.hubson.systemdyplomant.repository.remote.response_model;

import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubjectJoinedResponse {
    @SerializedName("subjects_joined")
    private List<Subject> results;

    public List<Subject> getResults() {
        return results;
    }

    public void setResults(List<Subject> results) {
        this.results = results;
    }
}
