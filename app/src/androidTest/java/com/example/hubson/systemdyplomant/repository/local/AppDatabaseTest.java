package com.example.hubson.systemdyplomant.repository.local;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.hubson.systemdyplomant.repository.local.dao.SubjectStatusDao;
import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AppDatabaseTest {
    private AppDatabase db;
    private SubjectStatusDao subjectStatusDao;
    private SubjectStatus subjectStatus;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        subjectStatusDao = db.getSubjectStatusDao();
        subjectStatus = new SubjectStatus(1, "Dostępny");
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void insertSubjectStatusAndRead() throws Exception {
        List<SubjectStatus> subjectStatuses = subjectStatusDao.findAllStatuses();
        assertEquals(0, subjectStatuses.size());

        subjectStatusDao.insert(subjectStatus);
        subjectStatuses = subjectStatusDao.findAllStatuses();
        assertEquals(1, subjectStatuses.size());
        assertEquals(subjectStatus.getIdSubjectStatus(), subjectStatuses.get(0).getIdSubjectStatus());
        assertEquals(subjectStatus.getStatusName(), subjectStatuses.get(0).getStatusName());
    }

    @Test
    public void deleteSubjectStatusAndRead() throws Exception {
        List<SubjectStatus> subjectStatuses = subjectStatusDao.findAllStatuses();
        assertEquals(0, subjectStatuses.size());

        subjectStatusDao.insert(subjectStatus);
        subjectStatuses = subjectStatusDao.findAllStatuses();
        assertEquals(1, subjectStatuses.size());
        assertEquals(subjectStatus.getIdSubjectStatus(), subjectStatuses.get(0).getIdSubjectStatus());
        assertEquals(subjectStatus.getStatusName(), subjectStatuses.get(0).getStatusName());

        subjectStatusDao.delete(subjectStatus);
        subjectStatuses = subjectStatusDao.findAllStatuses();
        assertEquals(0, subjectStatuses.size());
    }

    @Test
    public void updateSubjectStatusAndRead() throws Exception {
        List<SubjectStatus> subjectStatuses = subjectStatusDao.findAllStatuses();
        assertEquals(0, subjectStatuses.size());

        subjectStatusDao.insert(subjectStatus);
        subjectStatuses = subjectStatusDao.findAllStatuses();
        assertEquals(1, subjectStatuses.size());
        assertEquals(subjectStatus.getIdSubjectStatus(), subjectStatuses.get(0).getIdSubjectStatus());
        assertEquals(subjectStatus.getStatusName(), subjectStatuses.get(0).getStatusName());

        subjectStatus.setStatusName("Zajęty");
        subjectStatusDao.update(subjectStatus);
        subjectStatuses = subjectStatusDao.findAllStatuses();
        assertEquals(1, subjectStatuses.size());
        assertEquals(subjectStatus.getIdSubjectStatus(), subjectStatuses.get(0).getIdSubjectStatus());
        assertEquals(subjectStatus.getStatusName(), subjectStatuses.get(0).getStatusName());
    }
}
