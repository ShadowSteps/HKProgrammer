package com.shadows.hkprogrammer.android.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import com.shadows.hkprogrammer.android.activities.SelectProviderActivity;
import com.shadows.hkprogrammer.android.core.enums.AndroidProviderType;

/**
 * Created by John on 17.3.2016 г..
 */
public class ProviderSelectedListener extends Activity implements AdapterView.OnItemSelectedListener {
    private SelectProviderActivity activity;

    public ProviderSelectedListener(SelectProviderActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        AndroidProviderType Type = (AndroidProviderType)parent.getItemAtPosition(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
