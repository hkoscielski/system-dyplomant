package com.example.hubson.systemdyplomant.repository.remote.response_model;

import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GraduateResponse {
    @SerializedName("graduates")
    private List<Graduate> results;

    public List<Graduate> getResults() {
        return results;
    }

    public void setResults(List<Graduate> results) {
        this.results = results;
    }
}
