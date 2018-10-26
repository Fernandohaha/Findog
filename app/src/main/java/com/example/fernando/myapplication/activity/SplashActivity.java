package com.example.fernando.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fernando.myapplication.R;
import com.example.fernando.myapplication.utils.utils.services.DogService;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefs = getSharedPreferences(PREFS_LOGIN, 0);

        int LoginId = prefs.getInt("id",0);
        if (LoginId == 0){
            show(getActivity(), LoginActivity.class);
        }
        else
            show(getActivity(), MainActivity.class);

        finish();
    }
}
