package com.example.fernando.myapplication.utils.utils.effect;

import android.app.Activity;

public class ActivityEffectV5 implements ActivityEffect {
    public ActivityEffectV5() {
    }

    public void apply(Activity activity) {
        activity.overridePendingTransition(17432576, 17432577);
    }
}