package com.example.fernando.myapplication.utils.utils.effect;

import android.os.Build;

public class ActivityEffectFactory {
    public ActivityEffectFactory() {
    }

    public static com.example.fernando.myapplication.utils.utils.effect.ActivityEffect get() {
        int apiLevel = Build.VERSION.SDK_INT;
        return (ActivityEffect)(apiLevel > 4?new ActivityEffectV5():new ActivityEffectV4());
    }
}
