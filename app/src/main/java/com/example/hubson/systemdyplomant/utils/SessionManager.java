package com.example.hubson.systemdyplomant.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final Object LOCK = new Object();
    private static SessionManager sInstance;

    private SharedPreferences sessionData;

    private static final String PREF_NAME = "LoginData";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_STUDENT_ID = "StudentId";

    private SessionManager(Context context) {
        sessionData = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public synchronized static SessionManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SessionManager(context);
            }
        }
        return sInstance;
    }

    public void setLogin(boolean isLoggedIn) {
        sessionData.edit().putBoolean(KEY_IS_LOGGEDIN, isLoggedIn).apply();
        if(!isLoggedIn) sessionData.edit().remove(KEY_STUDENT_ID).apply();
    }

    public boolean isLoggedIn(){
        return sessionData.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void setUserId(int userId) {
        sessionData.edit().putInt(KEY_STUDENT_ID, userId).apply();
    }

    public int getUserId() {
        return sessionData.getInt(KEY_STUDENT_ID, 0);
    }
}
