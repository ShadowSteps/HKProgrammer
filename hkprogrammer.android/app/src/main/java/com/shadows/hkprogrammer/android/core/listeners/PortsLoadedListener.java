package com.shadows.hkprogrammer.android.core.listeners;


import android.app.Activity;
import android.drm.DrmEvent;
import android.drm.DrmManagerClient;

import com.shadows.hkprogrammer.android.activities.SelectProviderActivity;
import com.shadows.hkprogrammer.android.core.events.IActionEvent;
import com.shadows.hkprogrammer.android.core.events.PortsLoadedEvent;
import com.shadows.hkprogrammer.android.listeners.ProviderSelectedListener;

import java.util.EventListener;

/**
 * Created by John on 17.3.2016 г..
 */
public class PortsLoadedListener extends Activity implements IActionListener {
    private SelectProviderActivity activity;

    public PortsLoadedListener(SelectProviderActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onActionPerformed(IActionEvent event) {
        PortsLoadedEvent e = (PortsLoadedEvent) event;
        activity.LoadPortsIntoSpinner(e.Ports);
    }
}
