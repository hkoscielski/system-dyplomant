package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.FormOfStudies;

@Dao
public interface FormOfStudiesDao {
    @Query("SELECT * FROM Forms_Of_Studies WHERE id_form=:idForm")
    public FormOfStudies findFormById(final int idForm);

    @Insert
    public void insert(FormOfStudies formOfStudies);

    @Update
    public void update(FormOfStudies formOfStudies);

    @Delete
    public void delete(FormOfStudies formOfStudies);
}
