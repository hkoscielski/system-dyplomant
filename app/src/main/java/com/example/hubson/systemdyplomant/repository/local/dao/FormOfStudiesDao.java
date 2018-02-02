package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.FormOfStudies;

@Dao
public interface FormOfStudiesDao {
    @Query("SELECT * FROM Forms_Of_Studies WHERE id_form=:idForm")
    LiveData<FormOfStudies> loadFormById(final int idForm);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FormOfStudies formOfStudies);

    @Update
    void update(FormOfStudies formOfStudies);

    @Delete
    void delete(FormOfStudies formOfStudies);

    @Query("DELETE FROM Forms_Of_Studies")
    void deleteAll();
}
