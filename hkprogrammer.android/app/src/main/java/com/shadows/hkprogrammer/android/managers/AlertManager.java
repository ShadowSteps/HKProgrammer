package com.shadows.hkprogrammer.android.managers;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by John on 19.3.2016 г..
 */
public class AlertManager {
    public static void ShowErrorAlert(Activity parent,String error){
        Toast.makeText(parent.getApplicationContext(),error,Toast.LENGTH_LONG);
    }
}
