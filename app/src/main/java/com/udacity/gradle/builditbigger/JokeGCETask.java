package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.lang.ref.WeakReference;

class JokeGCETask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    WeakReference<OnTaskComplete> weakReference;
    private boolean success;

    public JokeGCETask(OnTaskComplete listener) {
        this.weakReference = new WeakReference<>(listener);
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(abstractGoogleClientRequest ->
                            abstractGoogleClientRequest.setDisableGZipContent(true));

            myApiService = builder.build();
        }

        try {
            success = true;
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            success = false;
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        OnTaskComplete listener = weakReference.get();
        if (listener != null) {
            listener.onTaskComplete(success, result);
        }
    }
}
