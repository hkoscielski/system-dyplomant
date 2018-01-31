package com.example.hubson.systemdyplomant.repository.remote.response_model;

import com.example.hubson.systemdyplomant.repository.local.entity.Declaration;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeclarationResponse {
    @SerializedName("declarations")
    private List<Declaration> results;

    public List<Declaration> getResults() {
        return results;
    }

    public void setResults(List<Declaration> results) {
        this.results = results;
    }
}
