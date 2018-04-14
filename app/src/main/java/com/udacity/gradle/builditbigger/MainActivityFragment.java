package com.udacity.gradle.builditbigger;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kalata.peter.jokelib.JokeManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.tv_joke)
    TextView tvJoke;

    @BindView(R.id.cv_joke)
    CardView cvJoke;

    @BindView(R.id.adView)
    AdView adView;

    private MainViewModel mainViewModel;
    private JokeManager jokeManager;

    public MainActivityFragment() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        jokeManager = new JokeManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        return root;
    }

    private void displayJoke() {
        if (!TextUtils.isEmpty(mainViewModel.joke)) {
            cvJoke.setVisibility(View.VISIBLE);
            tvJoke.setText(mainViewModel.joke);
        }
    }

    @OnClick(R.id.btn_tell_joke)
    void tellJoke(View view) {
        mainViewModel.joke = jokeManager.getJoke();
        displayJoke();
    }

}
