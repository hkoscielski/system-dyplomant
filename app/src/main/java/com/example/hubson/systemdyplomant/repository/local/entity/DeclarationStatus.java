package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Declaration_Statuses")
public class DeclarationStatus {
    @SerializedName("id_declaration_status")
    @PrimaryKey
    @ColumnInfo(name = "id_declaration_status")
    private int idDeclarationStatus;

    @SerializedName("status_name")
    @NonNull
    @ColumnInfo(name = "status_name")
    private String statusName;

    public DeclarationStatus() {}

    public DeclarationStatus(int idDeclarationStatus, @NonNull String statusName) {
        this.idDeclarationStatus = idDeclarationStatus;
        this.statusName = statusName;
    }

    public int getIdDeclarationStatus() {
        return idDeclarationStatus;
    }

    public void setIdDeclarationStatus(int idDeclarationStatus) {
        this.idDeclarationStatus = idDeclarationStatus;
    }

    @NonNull
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(@NonNull String statusName) {
        this.statusName = statusName;
    }
}
