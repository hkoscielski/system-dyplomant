package com.example.hubson.systemdyplomant.repository.local.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Klasa <code>DateConverters</code> stanowi zbiór statycznych metod konwertujących daty na
 * znaczniki czasu i odwrotnie.
 * <p>
 * Klasa ta jest wykorzystywana w encjach, aby możliwe było utrwalanie danych typu <code>Date</code>
 * w lokalnej bazie danych przy pomocy biblioteki Room.
 */

public class DateConverters {
    /**
     * Metoda pozwalająca na konwersję znacznika czasu na datę.
     * @param value milisekundy, które upłynęły od 1 stycznia 1970 roku i godziny 00:00:00.
     * @return data odpowiadająca podanym milisekundom.
     */
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    /**
     * Metoda pozwalająca na konwersję daty na znacznik czasu.
     * @param date data
     * @return milisekundy, które upłynęły od 1 stycznia 1970 roku i godziny 00:00:00 odpowiadające podanej dacie.
     */
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
