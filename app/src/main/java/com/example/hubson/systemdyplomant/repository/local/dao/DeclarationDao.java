package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.Declaration;

import java.util.List;

@Dao
public interface DeclarationDao {
    @Query("SELECT * FROM Declarations WHERE id_graduate=:idGraduate")
    public List<Declaration> findDeclarationsForGraduate(final int idGraduate);

    @Query("SELECT * FROM Declarations WHERE id_subject=:idSubject")
    public List<Declaration> findDeclarationsForSubject(final int idSubject);

    @Insert
    public void insert(Declaration declaration);

    @Update
    public void update(Declaration declaration);

    @Delete
    public void delete(Declaration declaration);
}
