package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Declarations")
public class Declaration {
    @PrimaryKey
    @ColumnInfo(name = "id_declaration")
    private int idDeclaration;

    @ColumnInfo(name = "id_subject")
    private int idSubject;

    @ColumnInfo(name = "id_supervisor")
    private int idSupervisor;

    @ColumnInfo(name = "id_graduate")
    private int idGraduate;

    @ColumnInfo(name = "language")
    private String language;

    @ColumnInfo(name = "purpose_range")
    private String purposeRange;

    @ColumnInfo(name = "short_desc")
    private String shortDesc;

    @ColumnInfo(name = "submit_date")
    private Date submitDate;

    @ColumnInfo(name = "supervisor_sign_date")
    private Date supervisorSignDate;

    @ColumnInfo(name = "id_declaration_status")
    private int idDeclarationStatus;
}
