package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

public class AsyncTaskTest extends AndroidTestCase {

    public void testAsyncTask() {
        try {
            JokeGCETask task = new JokeGCETask((success, result) -> {
                assertTrue(result != null && result.length() > 0);
                assertTrue(success);
            });
            task.execute();

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
