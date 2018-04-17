package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.kalata.peter.androidjokelib.JokeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements OnTaskComplete {


    @BindView(R.id.adView)
    AdView adView;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private InterstitialAd mInterstitialAd;
    private String joke;

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
        initAds();
        System.out.println("onCreateView");
        initInterstitialAds();
        return root;
    }

    private void displayJoke() {
        new JokeGCETask(this).execute();
    }

    @Override
    public void onTaskComplete(boolean success, String result) {
        progressBar.setVisibility(View.GONE);
        if (success) {
            joke = result;
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                startJokeActivity();
            }
        } else {
            Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
        }

    }

    private void startJokeActivity() {
        JokeActivity.startJokeActivity(getActivity(), joke);
    }

    private void initInterstitialAds() {
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ads));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                if (getActivity() != null) {
                    startJokeActivity();
                    loadAd();
                }

            }
        });
        loadAd();

    }

    private void loadAd() {
        mInterstitialAd.loadAd(new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build());
    }

    private void initAds() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
    }

    @OnClick(R.id.btn_tell_joke)
    void tellJoke() {
        progressBar.setVisibility(View.VISIBLE);
        displayJoke();
    }

}
