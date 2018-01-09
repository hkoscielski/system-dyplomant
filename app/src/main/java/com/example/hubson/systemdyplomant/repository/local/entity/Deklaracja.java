package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Deklaracje")
public class Deklaracja {
    @PrimaryKey
    @ColumnInfo(name = "id_deklaracji")
    private int idDeklaracji;

    @ColumnInfo(name = "id_tematu")
    private int idTematu;

    @ColumnInfo(name = "id_promotora")
    private int idPromotora;

    @ColumnInfo(name = "jezyk")
    private String jezyk;

    @ColumnInfo(name = "cel_zakres")
    private String celZakres;

    @ColumnInfo(name = "krotki_opis")
    private String krotkiOpis;

    @ColumnInfo(name = "data_zlozenia")
    private Date dataZlozenia;

    @ColumnInfo(name = "data_podpisu_promotora")
    private Date dataPodpisuPromotora;

    @ColumnInfo(name = "id_statusu_dekl")
    private int idStatusuDekl;
}
