package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Dyplomanci")
public class Dyplomant {
    @PrimaryKey
    @ColumnInfo(name = "id_dyplomanta")
    private int idDyplomanta;

    @ColumnInfo(name = "id_tematu")
    private int idTematu;

    @ColumnInfo(name = "imie")
    private String imie;

    @ColumnInfo(name = "nazwisko")
    private String nazwisko;

    @ColumnInfo(name = "nr_albumu")
    private String nrAlbumu;

    @ColumnInfo(name = "specjalnosc")
    private String specjalnosc;

    @ColumnInfo(name = "id_formy")
    private int idFormy;

    @ColumnInfo(name = "rok_studiow")
    private int rokStudiow;
}
