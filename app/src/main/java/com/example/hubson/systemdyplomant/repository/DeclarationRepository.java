package com.example.hubson.systemdyplomant.repository;

import com.example.hubson.systemdyplomant.repository.local.dao.DeclarationDao;
import com.example.hubson.systemdyplomant.repository.local.dao.DeclarationStatusDao;
import com.example.hubson.systemdyplomant.repository.remote.Webservice;
import com.example.hubson.systemdyplomant.utils.AppExecutors;

public class DeclarationRepository {
    private static final Object LOCK = new Object();
    private static DeclarationRepository sInstance;

    private final DeclarationDao declarationDao;
    private final DeclarationStatusDao declarationStatusDao;
    private final Webservice webservice;
    private final AppExecutors appExecutors;

    private DeclarationRepository(DeclarationDao declarationDao, DeclarationStatusDao declarationStatusDao,
                                 Webservice webservice, AppExecutors appExecutors) {
        this.declarationDao = declarationDao;
        this.declarationStatusDao = declarationStatusDao;
        this.webservice = webservice;
        this.appExecutors = appExecutors;
    }

    public synchronized static DeclarationRepository getInstance(DeclarationDao declarationDao, DeclarationStatusDao declarationStatusDao,
                                                                Webservice webservice, AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new DeclarationRepository(declarationDao, declarationStatusDao, webservice, executors);
            }
        }
        return sInstance;
    }
}
