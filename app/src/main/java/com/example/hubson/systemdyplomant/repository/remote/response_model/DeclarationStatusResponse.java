package com.example.hubson.systemdyplomant.repository.remote.response_model;

import com.example.hubson.systemdyplomant.repository.local.entity.DeclarationStatus;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeclarationStatusResponse {
    @SerializedName("declaration_statuses")
    private List<DeclarationStatus> results;

    public List<DeclarationStatus> getResults() {
        return results;
    }

    public void setResults(List<DeclarationStatus> results) {
        this.results = results;
    }
}
