package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Formy_Studiow")
public class FormaStudiow {
    @PrimaryKey
    @ColumnInfo(name = "id_formy")
    private int idFormy;

    @ColumnInfo(name = "nazwa_formy")
    private int nazwaFormy;
}
