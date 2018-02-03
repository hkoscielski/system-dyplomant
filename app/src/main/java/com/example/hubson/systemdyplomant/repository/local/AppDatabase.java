package com.example.hubson.systemdyplomant.repository.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.hubson.systemdyplomant.repository.local.dao.DeclarationDao;
import com.example.hubson.systemdyplomant.repository.local.dao.DeclarationStatusDao;
import com.example.hubson.systemdyplomant.repository.local.dao.FormOfStudiesDao;
import com.example.hubson.systemdyplomant.repository.local.dao.GraduateDao;
import com.example.hubson.systemdyplomant.repository.local.dao.SubjectDao;
import com.example.hubson.systemdyplomant.repository.local.dao.SubjectStatusDao;
import com.example.hubson.systemdyplomant.repository.local.dao.SupervisorDao;
import com.example.hubson.systemdyplomant.repository.local.entity.*;

/**
 * Klasa udostępniająca obiekty dostępu do danych znajdujących się w lokalnej bazie danych.
 */
@Database(entities = {Declaration.class, DeclarationStatus.class, Department.class, FormOfStudies.class,
        Graduate.class, Subject.class, SubjectStatus.class, Supervisor.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    /**
     * Obiekt używany do sterowania blokiem synchronizowanym w metodzie <code>getInstance(Context context)</code>.
     */
    private static final Object LOCK = new Object();
    /**
     * Instancja bazy danych
     */
    private static AppDatabase dbInstance;

    /**
     * Metoda zwracająca obiekt dostępu do danych dyplomantów
     * @return obiekt dostępu do danych dyplomantów
     */
    public abstract GraduateDao getGraduateDao();

    /**
     * Metoda zwracająca obiekt dostępu do danych form studiów
     * @return obiekt dostępu do danych form studiów
     */
    public abstract FormOfStudiesDao getFormOfStudiesDao();

    /**
     * Metoda zwracająca obiekt dostępu do danych tematów
     * @return obiekt dostępu do danych tematów
     */
    public abstract SubjectDao getSubjectDao();

    /**
     * Metoda zwracająca obiekt dostępu do danych statusów tematów
     * @return obiekt dostępu do danych statusów tematów
     */
    public abstract SubjectStatusDao getSubjectStatusDao();

    /**
     * Metoda zwracająca obiekt dostępu do danych promotorów
     * @return obiekt dostępu do danych promotorów
     */
    public abstract SupervisorDao getSupervisorDao();

    /**
     * Metoda zwracająca obiekt dostępu do danych deklaracji
     * @return obiekt dostępu do danych deklaracji
     */
    public abstract DeclarationDao getDeclarationDao();

    /**
     * Metoda zwracająca obiekt dostępu do danych statusów deklaracji
     * @return obiekt dostępu do danych statusów deklaracji
     */
    public abstract DeclarationStatusDao getDeclarationStatusDao();

    /**
     * Metoda zwracająca instancję bazy danych. W przypadku, gdy instancja nie została jeszcze utworzona,
     * metoda tworzy ją.
     * @param context kontekst aplikacji
     * @return instancja lokalnej bazy danych
     */
    public static AppDatabase getInstance(Context context) {
        if (dbInstance == null) {
            synchronized (LOCK) {
                if(dbInstance == null) {
                    dbInstance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).build();
                }
            }
        }
        return dbInstance;
    }
}
