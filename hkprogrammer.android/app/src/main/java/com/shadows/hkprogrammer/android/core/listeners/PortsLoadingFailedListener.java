package com.shadows.hkprogrammer.android.core.listeners;

import android.os.Message;

import com.shadows.hkprogrammer.android.handlers.SelectProviderActivityHandler;
import com.shadows.hkprogrammer.core.client.events.TaskOnErrorEvent;
import com.shadows.hkprogrammer.core.client.listeners.ATaskOnErrorListener;

/**
 * Created by John on 19.3.2016 г..
 */
public class PortsLoadingFailedListener extends ATaskOnErrorListener {
    private final SelectProviderActivityHandler handler;

    public PortsLoadingFailedListener(SelectProviderActivityHandler handler) {
        this.handler = handler;
    }
    @Override
    public void onError(TaskOnErrorEvent result) {
        Exception e = result.error;
        Message errorMessage = handler.obtainMessage(SelectProviderActivityHandler.failState, e);
        errorMessage.sendToTarget();
    }
}