package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Declaration_Statuses")
public class DeclarationStatus {
    @PrimaryKey
    @ColumnInfo(name = "id_declaration_status")
    private int id_declaration_status;

    @ColumnInfo(name = "status_name")
    private String statusName;
}
