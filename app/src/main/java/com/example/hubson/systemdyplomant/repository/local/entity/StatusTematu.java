package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Statusy_Tematu")
public class StatusTematu {
    @PrimaryKey
    @ColumnInfo(name = "id_statusu_tem")
    private int idStatusuTem;

    @ColumnInfo(name = "nazwa_statusu")
    private String nazwaStatusu;
}
