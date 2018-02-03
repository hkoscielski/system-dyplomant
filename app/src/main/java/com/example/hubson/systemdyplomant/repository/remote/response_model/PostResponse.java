package com.example.hubson.systemdyplomant.repository.remote.response_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * <code>PostResponse</code> reprezentuje zdeserializowane ciało pochodzące z odpowiedzi REST API na żądanie typu POST.
 */
public class PostResponse {
    /**
     * Informacja, czy żądanie zakończyło się sukcesem czy nie.
     */
    @SerializedName("success")
    @Expose
    private Boolean success;

    /**
     * Szczegółowa wiadomość zwrotna.
     */
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * Tworzy nową instancję dla odpowiedzi REST API.
     * @param success Informacja, czy żądanie zakończyło się sukcesem czy nie
     * @param message Szczegółowa wiadomość zwrotna
     */
    public PostResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Metoda zwracająca informację, czy żądanie zakończyło się sukcesem czy nie.
     * @return Informacja, czy żądanie zakończyło się sukcesem czy nie
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * Metoda ustawiająca informację, czy żądanie zakończyło się sukcesem czy nie.
     * @param success nowa informacja zwrotna
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * Metoda zwracająca szczegółową wiadomość zwrotną.
     * @return Szczegółowa wiadomość zwrotna
     */
    public String getMessage() {
        return message;
    }

    /**
     * Metoda ustawiająca szczegółową wiadomość zwrotną.
     * @param message nowa szczegółowa wiadomość zwrotna
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
