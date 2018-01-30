package com.example.hubson.systemdyplomant.repository.remote.response_model;

import com.example.hubson.systemdyplomant.repository.local.entity.Supervisor;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SupervisorResponse {
    @SerializedName("supervisors")
    private List<Supervisor> results;

    public List<Supervisor> getResults() {
        return results;
    }

    public void setResults(List<Supervisor> results) {
        this.results = results;
    }
}
