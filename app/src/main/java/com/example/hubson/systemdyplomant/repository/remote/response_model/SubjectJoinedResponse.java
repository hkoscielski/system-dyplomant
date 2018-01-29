package com.example.hubson.systemdyplomant.repository.remote.response_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubjectJoinedResponse {
    @SerializedName("subjects_joined")
    private List<SubjectJoined> results;

    public List<SubjectJoined> getResults() {
        return results;
    }

    public void setResults(List<SubjectJoined> results) {
        this.results = results;
    }
}
