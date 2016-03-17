package com.shadows.hkprogrammer.android.managers;

import android.app.Activity;
import android.app.AlertDialog;

import com.shadows.hkprogrammer.android.listeners.ConnectEventListener;

/**
 * Created by John on 17.3.2016 г..
 */
public class DialogManager {
    public static void ShowSelectProviderDialog(Activity Parent){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Parent);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Моля изберете връзка.");
        alertDialogBuilder.setPositiveButton("Свържи се",new ConnectEventListener());
    }
}
