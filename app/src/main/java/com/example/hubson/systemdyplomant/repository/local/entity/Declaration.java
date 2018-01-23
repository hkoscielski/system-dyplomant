package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.hubson.systemdyplomant.repository.local.converters.DateConverters;

import java.util.Date;

@Entity(tableName = "Declarations", foreignKeys = {
        @ForeignKey(entity = Subject.class,
                    parentColumns = "id_subject",
                    childColumns = "id_subject"),
        @ForeignKey(entity = Graduate.class,
                    parentColumns = "id_graduate",
                    childColumns = "id_graduate"),
        @ForeignKey(entity = DeclarationStatus.class,
                    parentColumns = "id_declaration_status",
                    childColumns = "id_declaration_status")
})
@TypeConverters(DateConverters.class)
public class Declaration {
    @PrimaryKey
    @ColumnInfo(name = "id_declaration")
    private int idDeclaration;

    @ColumnInfo(name = "id_subject")
    private int idSubject;

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

    public int getIdDeclaration() {
        return idDeclaration;
    }

    public void setIdDeclaration(int idDeclaration) {
        this.idDeclaration = idDeclaration;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public int getIdGraduate() {
        return idGraduate;
    }

    public void setIdGraduate(int idGraduate) {
        this.idGraduate = idGraduate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPurposeRange() {
        return purposeRange;
    }

    public void setPurposeRange(String purposeRange) {
        this.purposeRange = purposeRange;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getSupervisorSignDate() {
        return supervisorSignDate;
    }

    public void setSupervisorSignDate(Date supervisorSignDate) {
        this.supervisorSignDate = supervisorSignDate;
    }

    public int getIdDeclarationStatus() {
        return idDeclarationStatus;
    }

    public void setIdDeclarationStatus(int idDeclarationStatus) {
        this.idDeclarationStatus = idDeclarationStatus;
    }
}
