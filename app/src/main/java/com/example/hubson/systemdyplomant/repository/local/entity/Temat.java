package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Tematy")
public class Temat {
    @PrimaryKey
    @ColumnInfo(name = "id_tematu")
    private int idTematu;

    @ColumnInfo(name = "id_promotora")
    private int idPromotora;

    @ColumnInfo(name = "temat_pl")
    private String tematPl;

    @ColumnInfo(name = "temat_en")
    private String tematEn;

    @ColumnInfo(name = "ile_zajetych")
    private int ileZajetych;

    @ColumnInfo(name = "limit_osob")
    private int limitOsob;

    @ColumnInfo(name = "id_statusu_tem")
    private int idStatusuTem;
}
