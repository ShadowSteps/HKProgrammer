package com.shadows.hkprogrammer.android.core.thread;

import com.shadows.hkprogrammer.android.activities.SelectProviderActivity;
import com.shadows.hkprogrammer.android.core.listeners.PortsLoadingFailedListener;
import com.shadows.hkprogrammer.android.core.listeners.PortsLoadingStartListener;
import com.shadows.hkprogrammer.android.core.listeners.PortsLoadingSuccessListener;
import com.shadows.hkprogrammer.android.handlers.SelectProviderActivityHandler;
import com.shadows.hkprogrammer.core.client.GetPortsListTask;
import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;

/**
 * Created by John on 17.3.2016 г..
 */
public class ProviderThreadManager {
    private GetPortsListTask getPortsListWorker;
    private final ICommunicationProvider provider;
    private final SelectProviderActivityHandler handler;
    public ProviderThreadManager(ICommunicationProvider provider, SelectProviderActivityHandler handler) {
        this.getPortsListWorker = new GetPortsListTask(provider);
        this.provider = provider;
        this.handler = handler;
    }

    public void RunGetPortsTask(){
        getPortsListWorker = new GetPortsListTask(provider);
        getPortsListWorker.onStart = new PortsLoadingStartListener();
        getPortsListWorker.onSuccess = new PortsLoadingSuccessListener(handler);
        getPortsListWorker.onError = new PortsLoadingFailedListener(handler);
        new Thread(getPortsListWorker).start();
    }

}
