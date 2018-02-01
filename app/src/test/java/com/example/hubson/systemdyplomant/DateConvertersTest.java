package com.example.hubson.systemdyplomant;

import com.example.hubson.systemdyplomant.repository.local.converters.DateConverters;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;

public class DateConvertersTest {

    @Test
    public void testConvertDateToLong() {
        assertEquals(null, DateConverters.dateToTimestamp(null));
        assertEquals(new Long(0L), DateConverters.dateToTimestamp(new Date(0L)));
        assertEquals(new Long(987654321L), DateConverters.dateToTimestamp(new Date(987654321L)));
        assertEquals(new Long(123456789L), DateConverters.dateToTimestamp(new Date(123456789L)));
    }

    @Test
    public void testConvertLongToDate() {
        assertEquals(null, DateConverters.fromTimestamp(null));
        assertEquals(new Date(0L), DateConverters.fromTimestamp(0L));
        assertEquals(new Date(987654321L), DateConverters.fromTimestamp(987654321L));
        assertEquals(new Date(123456789L), DateConverters.fromTimestamp(123456789L));
    }
}
