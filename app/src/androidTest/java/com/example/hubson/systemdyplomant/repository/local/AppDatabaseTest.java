package com.example.hubson.systemdyplomant.repository.local;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.hubson.systemdyplomant.repository.local.dao.FormOfStudiesDao;
import com.example.hubson.systemdyplomant.repository.local.dao.GraduateDao;
import com.example.hubson.systemdyplomant.repository.local.entity.FormOfStudies;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AppDatabaseTest {
    private GraduateDao graduateDao;
    private FormOfStudiesDao formOfStudiesDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        graduateDao = db.getGraduateDao();
        formOfStudiesDao = db.getFormOfStudiesDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void insertGraduateAndRead() throws Exception {
        FormOfStudies formOfStudies = new FormOfStudies();
        formOfStudies.setIdForm(1);
        formOfStudies.setFormName("NZ");
        formOfStudiesDao.insert(formOfStudies);

        Graduate graduate = new Graduate();
        graduate.setIdGraduate(1);
        graduate.setName("Hubert");
        graduate.setSurname("Kościelski");
        graduate.setIdSubject(null);
        graduate.setSpeciality(null);
        graduate.setStudentNo("228172");
        graduate.setYearOfStudies(3);
        graduate.setIdForm(1);
        graduateDao.insert(graduate);

        Graduate g = graduateDao.findGraduateById(graduate.getIdGraduate());
        FormOfStudies f = formOfStudiesDao.findFormById(graduate.getIdForm());

        assertEquals(graduate.getName(), g.getName());
        assertEquals(formOfStudies.getFormName(), f.getFormName());
    }

    @Test
    public void insertAndClearFormOfStudies() {
        FormOfStudies formOfStudies = new FormOfStudies();
        formOfStudies.setIdForm(1);
        formOfStudies.setFormName("NZ");
        formOfStudiesDao.insert(formOfStudies);

        FormOfStudies f = formOfStudiesDao.findFormById(1);
        assertEquals(formOfStudies.getFormName(), f.getFormName());

        formOfStudiesDao.delete(f);
        FormOfStudies fDelete = formOfStudiesDao.findFormById(1);
        assertEquals(null, fDelete);
    }

    @Test
    public void updateFormOfStudies() {
        FormOfStudies formOfStudies = new FormOfStudies();
        formOfStudies.setIdForm(1);
        formOfStudies.setFormName("NZ");
        formOfStudiesDao.insert(formOfStudies);

        FormOfStudies f = formOfStudiesDao.findFormById(1);
        assertEquals(formOfStudies.getFormName(), f.getFormName());

        formOfStudies.setFormName("ST");
        formOfStudiesDao.update(formOfStudies);

        f = formOfStudiesDao.findFormById(1);
        assertEquals(formOfStudies.getFormName(), f.getFormName());
    }
}
