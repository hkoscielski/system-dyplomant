package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Declaration_Statuses")
public class DeclarationStatus {
    @PrimaryKey
    @ColumnInfo(name = "id_declaration_status")
    private int idDeclarationStatus;

    @ColumnInfo(name = "status_name")
    private String statusName;

    public int getIdDeclarationStatus() {
        return idDeclarationStatus;
    }

    public void setIdDeclarationStatus(int idDeclarationStatus) {
        this.idDeclarationStatus = idDeclarationStatus;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
