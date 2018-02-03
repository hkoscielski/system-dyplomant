package com.example.hubson.systemdyplomant.repository.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;

import java.util.List;


/**
 * Interfejs dostarczający metody związane z dostępem do danych statusów tematów dla lokalnej bazy danych.
 */
@Dao
public interface SubjectStatusDao {
    /**
     * Metoda pozwalająca na pobranie listy wszystkich statusów wraz z możliwością obserwowania zmian tych danych
     * poprzez opakowanie <code>List</code> klasą <code>LiveData</code>
     * @return lista statusów tematów z możliwością obserwowania zmian.
     */
    @Query("SELECT * FROM Subject_Statuses")
    LiveData<List<SubjectStatus>> loadAllStatuses();

    /**
     * Metoda pozwalająca na pobranie listy wszystkich statusów
     * @return lista statusów tematów.
     */
    @Query("SELECT * FROM Subject_Statuses")
    List<SubjectStatus> findAllStatuses();

    /**
     * Metoda pozwalająca na pobranie statusu tematu o podanej nazwie wraz z możliwością obserwowania zmian tych danych
     * poprzez opakowanie <code>SubjectStatus</code> klasą <code>LiveData</code>
     * @return status tematu z możliwością obserwowania zmian.
     */
    @Query("SELECT * FROM Subject_Statuses WHERE status_name=:statusName")
    LiveData<SubjectStatus> loadStatusByName(String statusName);

    /**
     * Metoda pozwalająca na zapisanie do lokalnej bazy danych listy statusów tematów.
     * <p>
     * W przypadku konfliktu danych, istniejące obiekty są zastępowane nowymi.
     * @param subjectStatuses lista statusów tematów
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SubjectStatus> subjectStatuses);

    /**
     * Metoda pozwalająca na zapisanie do lokalnej bazy danych listy statusów tematów.
     * <p>
     * W przypadku konfliktu danych, istniejące obiekty są zastępowane nowymi.
     * @param subjectStatus status tematu
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SubjectStatus subjectStatus);

    /**
     * Metoda pozwalająca na aktualizację rekordu znajdującego się w lokalnej bazie danych
     * odpowiadającego obiektowi przekazywanemu w parametrze.
     * @param subjectStatus obiekt, którego odpowiednik w bazie danych ma zostać zaktualizowany.
     */
    @Update
    void update(SubjectStatus subjectStatus);

    /**
     * Metoda pozwalająca na usunięcie rekordu znajdującego się w lokalnej bazie danych
     * odpowiadającego obiektowi przekazywanemu w parametrze.
     * @param subjectStatus obiekt, którego odpowiednik w bazie danych ma zostać usunięty.
     */
    @Delete
    void delete(SubjectStatus subjectStatus);

    /**
     * Metoda pozwalająca na usunięcie wszystkich statusów tematów z lokalnej bazy danych
     */
    @Query("DELETE FROM Subject_Statuses")
    void deleteAll();
}
