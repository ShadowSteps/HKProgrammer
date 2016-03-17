package com.shadows.hkprogrammer.android.core.thread;

import com.shadows.hkprogrammer.android.core.events.PortsLoadedEvent;
import com.shadows.hkprogrammer.android.core.listeners.PortsLoadedListener;
import com.shadows.hkprogrammer.android.managers.DialogManager;
import com.shadows.hkprogrammer.core.client.GetPortsListTask;
import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by John on 17.3.2016 г..
 */
public class ProviderThreadManager {
    private final GetPortsListTask getPortsListWorker;
    private final ICommunicationProvider provider;
    public PortsLoadedListener onPortsLoaded;

    public ProviderThreadManager(ICommunicationProvider provider) {
        this.getPortsListWorker = new GetPortsListTask(provider);
        this.provider = provider;
    }

    public void RunGetPortsTask(){

        getPortsListWorker.setOnRunning();
        getPortsListWorker.setOnSucceeded((e) -> {
            DialogManager.CloseLoadingDialog();
            boolean result = false;
            try{
                result = getPortsListWorker.get();
            }
            catch(InterruptedException | ExecutionException exp){
            }
            if (!result)
                AlertManager.AlertError("Could not get ports for provider!");
            if(onPortsLoaded != null)
                onPortsLoaded.onActionPerformed(
                        new PortsLoadedEvent(
                                getPortsListWorker.getPorts()
                        )
                );
        });
        getPortsListWorker.setOnFailed((e) -> {
            DialogManager.CloseLoadingDialog();
            try{
                boolean result = getPortsListWorker.get();
            }
            catch(InterruptedException | ExecutionException exp){
                AlertManager.AlertError("Could not get ports for provider!");
            }
        });
        new Thread(getPortsListWorker).start();
    }

}
