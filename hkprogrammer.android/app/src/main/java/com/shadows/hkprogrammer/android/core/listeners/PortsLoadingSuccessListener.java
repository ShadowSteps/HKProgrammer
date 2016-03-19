package com.shadows.hkprogrammer.android.core.listeners;

import com.shadows.hkprogrammer.android.handlers.SelectProviderActivityHandler;
import com.shadows.hkprogrammer.core.client.events.TaskOnSuccessEvent;
import com.shadows.hkprogrammer.core.client.listeners.ATaskOnSuccessListener;

import java.util.ArrayList;

/**
 * Created by John on 19.3.2016 г..
 */
public class PortsLoadingSuccessListener extends ATaskOnSuccessListener<ArrayList<String>> {
    private final SelectProviderActivityHandler handler;

    public PortsLoadingSuccessListener(SelectProviderActivityHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onSuccess(TaskOnSuccessEvent<ArrayList<String>> result) {
        handler.obtainMessage(SelectProviderActivityHandler.loadState,result);
    }
}