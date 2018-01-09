package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Promotorzy")
public class Promotor {
    @PrimaryKey
    @ColumnInfo(name = "id_promotora")
    private int idPromotora;

    @ColumnInfo(name = "tytul_naukowy")
    private String tytulNaukowy;

    @ColumnInfo(name = "imie")
    private String imie;

    @ColumnInfo(name = "nazwisko")
    private String nazwisko;

    @ColumnInfo(name = "id_katedry")
    private int idKatedry;
}
