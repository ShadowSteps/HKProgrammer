package com.shadows.hkprogrammer.android.handlers;


import android.os.Handler;

import com.shadows.hkprogrammer.android.activities.SelectProviderActivity;
import com.shadows.hkprogrammer.android.managers.AlertManager;

import java.util.ArrayList;

/**
 * Created by John on 19.3.2016 г..
 */
public class SelectProviderActivityHandler extends Handler {
    public static final int loadState = 11;
    public static final int failState = 12;
    private final SelectProviderActivity activity;

    public SelectProviderActivityHandler(SelectProviderActivity activity) {
        this.activity = activity;
    }
    @Override
    public void handleMessage(android.os.Message msg) {
        switch (msg.what){
            case loadState:
                ArrayList<String> ports = (ArrayList<String>) msg.obj;
                activity.LoadPortsIntoSpinner(ports);
                break;
            case failState:
                Exception error = (Exception) msg.obj;
                AlertManager.ShowErrorAlert(activity,"Could not get ports!");
                break;
        }
    }
}
