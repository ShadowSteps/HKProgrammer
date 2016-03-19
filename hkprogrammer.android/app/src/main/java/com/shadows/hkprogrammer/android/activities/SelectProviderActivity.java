package com.shadows.hkprogrammer.android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.shadows.hkprogrammer.android.R;
import com.shadows.hkprogrammer.android.core.enums.AndroidProviderType;
import com.shadows.hkprogrammer.android.core.providers.BluetoothProvider;
import com.shadows.hkprogrammer.android.core.thread.ProviderThreadManager;
import com.shadows.hkprogrammer.android.handlers.SelectProviderActivityHandler;
import com.shadows.hkprogrammer.android.listeners.ProviderSelectedListener;
import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;

import java.util.ArrayList;

public class SelectProviderActivity extends AppCompatActivity {
    private ICommunicationProvider selectedProvider;
    private Spinner providerType;
    private Spinner providerSelect;
    private ProviderThreadManager manager;
    private void InitializeControls(){
        providerType = (Spinner)findViewById(R.id.providerTypeSpinner);
        ArrayAdapter<AndroidProviderType> adapter = new ArrayAdapter<AndroidProviderType>(
                this,
                android.R.layout.simple_spinner_item,
                AndroidProviderType.values());
        providerType.setAdapter(adapter);
        providerType.setOnItemSelectedListener(new ProviderSelectedListener(this));
        providerSelect = (Spinner)findViewById(R.id.providerSelect);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_provider);
        InitializeControls();
    }

    public ICommunicationProvider getSelectedProvider() {
        return selectedProvider;
    }

    public void LoadPortsIntoSpinner(ArrayList<String> ports){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                ports);
        providerSelect.setAdapter(adapter);
    }

    public void LoadProvider(AndroidProviderType type){
        switch (type){
            case Bluetooth:
                selectedProvider = new BluetoothProvider();
                break;
        }
        manager = new ProviderThreadManager(selectedProvider,new SelectProviderActivityHandler(this));
        manager.RunGetPortsTask();
    }
}
