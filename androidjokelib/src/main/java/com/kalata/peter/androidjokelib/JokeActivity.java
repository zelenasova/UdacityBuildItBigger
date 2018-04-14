package com.kalata.peter.androidjokelib;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke";

    public static void startJokeActivity(Context context, String joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(EXTRA_JOKE, joke);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if (getIntent().getStringExtra(EXTRA_JOKE) != null) {
            TextView tvJoke = findViewById(R.id.tv_joke);
            tvJoke.setText(getIntent().getStringExtra(EXTRA_JOKE));
        }


    }
}
