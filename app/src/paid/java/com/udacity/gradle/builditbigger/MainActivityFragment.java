package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kalata.peter.androidjokelib.JokeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements OnTaskComplete {

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    public MainActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    private void displayJoke() {
        new JokeGCETask(this).execute();
    }

    @Override
    public void onTaskComplete(boolean success, String result) {
        progressBar.setVisibility(View.GONE);
        if (success) {
            JokeActivity.startJokeActivity(getActivity(), result);
        } else {
            Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.btn_tell_joke)
    void tellJoke() {
        progressBar.setVisibility(View.VISIBLE);
        displayJoke();
    }

}
