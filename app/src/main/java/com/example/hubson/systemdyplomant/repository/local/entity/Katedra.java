package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Katedry")
public class Katedra {
    @PrimaryKey
    @ColumnInfo(name = "id_katedry")
    private int idKatedry;

    @ColumnInfo(name = "nazwa_katedry")
    private int nazwaKatedry;
}
