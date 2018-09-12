package com.example.marcin.registrationvisitapp.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.marcin.registrationvisitapp.ui.visit.Visit;
import com.example.marcin.registrationvisitapp.database.VisitDao;
import com.example.marcin.registrationvisitapp.database.VisitsDatabase;

import java.util.List;

public class VisitRepository {
    private VisitDao mVisitDao;
    private LiveData<List<Visit>> mAllVisits;

    public VisitRepository(Application application) {
        VisitsDatabase db = VisitsDatabase.getDatabase(application);
        mVisitDao = db.visitDao();
        mAllVisits = mVisitDao.loadAllVisits();
    }

    public LiveData<List<Visit>> getAllVisits() {
        return mAllVisits;
    }

    public void insert(Visit visit){
        new insertAsyncTask(mVisitDao).execute(visit);
    }


    private static class insertAsyncTask extends AsyncTask<Visit,Void, Void>{

        private VisitDao mAsyncTaskVisitDao;

        insertAsyncTask(VisitDao dao){
            mAsyncTaskVisitDao = dao;
        }

        @Override
        protected Void doInBackground(Visit... visits) {
            mAsyncTaskVisitDao.insertVisit(visits[0]);
            return null;
        }
    }
}
