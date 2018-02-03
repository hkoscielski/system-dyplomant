package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Klasa reprezentuje status tematu pracy dyplomowej.
 * <p>
 * <code>SubjectStatus</code> jest klasą odpowiadającą tabeli <i>Subject_Statuses</i>
 * oraz wykorzystywana do odbioru danych z zewnętrznej bazy danych.
 */
@Entity(tableName = "Subject_Statuses")
public class SubjectStatus {
    /**
     * Klucz główny relacji <i>Subject_Statuses</i>.
     */
    @SerializedName("id_subject_status")
    @PrimaryKey
    @ColumnInfo(name = "id_subject_status")
    private int idSubjectStatus;

    /**
     * Nazwa statusu
     */
    @SerializedName("status_name")
    @NonNull
    @ColumnInfo(name = "status_name")
    private String statusName;

    /**
     * Konstruktor domyślny
     */
    public SubjectStatus() {}

    /**
     *
     * @param idSubjectStatus identyfikator statusu tematu.
     * @param statusName nazwa statusu tematu
     */
    public SubjectStatus(int idSubjectStatus, @NonNull String statusName) {
        this.idSubjectStatus = idSubjectStatus;
        this.statusName = statusName;
    }

    /**
     * Metoda zwracająca identyfikator statusu tematu.
     * @return identyfikator statusu tematu
     */
    public int getIdSubjectStatus() {
        return idSubjectStatus;
    }

    /**
     * Metoda ustawiająca identyfikator statusu tematu.
     * @param idSubjectStatus nowa wartość dla identyfikatora statusu tematu
     */
    public void setIdSubjectStatus(int idSubjectStatus) {
        this.idSubjectStatus = idSubjectStatus;
    }

    /**
     * Metoda zwracająca nazwę statusu tematu.
     * @return nazwa statusu tematu
     */
    @NonNull
    public String getStatusName() {
        return statusName;
    }

    /**
     * Metoda ustawiająca nazwę statusu tematu.
     * @param statusName nowa nazwa statusu tematu
     */
    public void setStatusName(@NonNull String statusName) {
        this.statusName = statusName;
    }
}
