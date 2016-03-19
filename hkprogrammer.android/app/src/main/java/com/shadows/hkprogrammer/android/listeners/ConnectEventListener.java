package com.shadows.hkprogrammer.android.listeners;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.shadows.hkprogrammer.android.activities.SelectProviderActivity;
import com.shadows.hkprogrammer.android.managers.AlertManager;
import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;

/**
 * Created by John on 17.3.2016 г..
 */
public class ConnectEventListener implements View.OnClickListener {
    private final ICommunicationProvider provider;
    private final SelectProviderActivity activity;

    public ConnectEventListener(ICommunicationProvider provider, SelectProviderActivity activity) {
        this.provider = provider;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Button clicked = (Button)v;
        Spinner provider = activity.getProviderSelect();
        String selected;
        if (provider.getSelectedItem()!=null){
            try {
                selected = (String)provider.getSelectedItem();
                this.provider.OpenConnection(selected);
            }
            catch (Exception e){
                AlertManager.ShowErrorAlert(activity,"Could not connect to device!");
                this.provider.CloseConnection();
            }
        }
    }
}
