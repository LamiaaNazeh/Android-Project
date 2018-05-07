package com.android.abdelmun3m.tripplaner;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.AsyncTask;
import android.widget.Toast;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class TripJobService extends JobService {

    BackgroundTask backgroundTask;


    @Override
    public boolean onStartJob(final JobParameters job) {
        backgroundTask = new BackgroundTask(){

            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(getApplicationContext(),"message from background task : " + s,Toast.LENGTH_LONG).show();
                jobFinished(job,false);

            }
        };
        backgroundTask.execute();
        return true;

    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }

    public static class BackgroundTask extends AsyncTask<Void , Void , String>{

        @Override
        protected String doInBackground(Void... voids) {
            return "Hello from background";
        }
    }
}
