package com.example.hubson.systemdyplomant.repository.remote.response_model;

import com.example.hubson.systemdyplomant.repository.local.entity.Supervisor;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <code>SupervisorResponse</code> reprezentuje zdeserializowane ciało
 * pochodzące z odpowiedzi REST API na żądanie typu GET listy promotorów.
 */
public class SupervisorResponse {
    /**
     * Lista promotorów stanowiąca zdeserializowane ciało odpowiedzi
     */
    @SerializedName("supervisors")
    private List<Supervisor> results;

    /**
     * Metoda zwracająca listę promotorów.
     * @return lista promotorów
     */
    public List<Supervisor> getResults() {
        return results;
    }

    /**
     * Metoda ustawiająca listę promotorów.
     * @param results nowa lista promotorów
     */
    public void setResults(List<Supervisor> results) {
        this.results = results;
    }
}
