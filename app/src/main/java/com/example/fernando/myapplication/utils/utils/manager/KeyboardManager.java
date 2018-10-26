package com.example.fernando.myapplication.utils.utils.manager;

import android.app.Activity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by aldv on 24/07/2016.
 */
public class KeyboardManager {

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.d("FODEU teclado", e.getMessage());
        }
    }
}
