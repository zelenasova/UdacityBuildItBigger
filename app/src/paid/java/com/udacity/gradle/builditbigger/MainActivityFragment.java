package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalata.peter.androidjokelib.JokeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements OnTaskComplete {

    public MainActivityFragment() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

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

    @OnClick(R.id.btn_tell_joke)
    void tellJoke(View view) {
        displayJoke();
    }

    @Override
    public void onTaskComplete(String joke) {
        JokeActivity.startJokeActivity(getActivity(), joke);
    }
}
