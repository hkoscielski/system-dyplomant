package com.example.hubson.systemdyplomant.repository.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.hubson.systemdyplomant.repository.local.converters.DateConverters;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("id_declaration")
    @PrimaryKey
    @ColumnInfo(name = "id_declaration")
    private int idDeclaration;

    @SerializedName("id_subject")
    @NonNull
    @ColumnInfo(name = "id_subject")
    private Integer idSubject;

    @SerializedName("id_graduate")
    @NonNull
    @ColumnInfo(name = "id_graduate")
    private Integer idGraduate;

    @SerializedName("language")
    @NonNull
    @ColumnInfo(name = "language")
    private String language;

    @SerializedName("purpose_range")
    @NonNull
    @ColumnInfo(name = "purpose_range")
    private String purposeRange;

    @SerializedName("short_desc")
    @NonNull
    @ColumnInfo(name = "short_desc")
    private String shortDesc;

    @SerializedName("submit_date")
    @ColumnInfo(name = "submit_date")
    private Date submitDate;

    @SerializedName("supervisor_sign_date")
    @ColumnInfo(name = "supervisor_sign_date")
    private Date supervisorSignDate;

    @SerializedName("id_declaration_status")
    @NonNull
    @ColumnInfo(name = "id_declaration_status")
    private Integer idDeclarationStatus;

    public Declaration() {};

    public Declaration(int idDeclaration, @NonNull Integer idSubject, @NonNull Integer idGraduate, @NonNull String language, @NonNull String purposeRange, @NonNull String shortDesc, Date submitDate, Date supervisorSignDate, @NonNull Integer idDeclarationStatus) {
        this.idDeclaration = idDeclaration;
        this.idSubject = idSubject;
        this.idGraduate = idGraduate;
        this.language = language;
        this.purposeRange = purposeRange;
        this.shortDesc = shortDesc;
        this.submitDate = submitDate;
        this.supervisorSignDate = supervisorSignDate;
        this.idDeclarationStatus = idDeclarationStatus;
    }

    public int getIdDeclaration() {
        return idDeclaration;
    }

    public void setIdDeclaration(int idDeclaration) {
        this.idDeclaration = idDeclaration;
    }

    @NonNull
    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(@NonNull Integer idSubject) {
        this.idSubject = idSubject;
    }

    @NonNull
    public Integer getIdGraduate() {
        return idGraduate;
    }

    public void setIdGraduate(@NonNull Integer idGraduate) {
        this.idGraduate = idGraduate;
    }

    @NonNull
    public String getLanguage() {
        return language;
    }

    public void setLanguage(@NonNull String language) {
        this.language = language;
    }

    @NonNull
    public String getPurposeRange() {
        return purposeRange;
    }

    public void setPurposeRange(@NonNull String purposeRange) {
        this.purposeRange = purposeRange;
    }

    @NonNull
    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(@NonNull String shortDesc) {
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

    @NonNull
    public Integer getIdDeclarationStatus() {
        return idDeclarationStatus;
    }

    public void setIdDeclarationStatus(@NonNull Integer idDeclarationStatus) {
        this.idDeclarationStatus = idDeclarationStatus;
    }
}
