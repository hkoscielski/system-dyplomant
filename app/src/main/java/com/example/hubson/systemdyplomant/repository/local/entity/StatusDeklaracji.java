package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Statusy_Deklaracji")
public class StatusDeklaracji {
    @PrimaryKey
    @ColumnInfo(name = "id_statusu_dekl")
    private int id_statusu_dekl;

    @ColumnInfo(name = "nazwa_statusu")
    private String nazwaStatusu;
}
