/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.managers;

import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import com.shadows.hkprogrammer.windows.controls.eventlistners.PortsLoadedListner;
import com.shadows.hkprogrammer.windows.controls.events.PortsLoadedEvent;
import com.shadows.hkprogrammer.windows.core.tasks.GetPortsListTask;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author John
 */
public class ProviderWorkerManager {
    private final GetPortsListTask getPortsListWorker;
    private final ICommunicationProvider provider;
    public PortsLoadedListner onPortsLoaded;

    public ProviderWorkerManager(ICommunicationProvider provider) {
        this.getPortsListWorker = new GetPortsListTask(provider);
        this.provider = provider;
    }
    
    public void RunGetPortsTask(){
        getPortsListWorker.setOnRunning((e) -> {
            try {
                DialogManager.ShowLoadingDialog();
            } catch (IOException ex) {               
            }
        });
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
                    onPortsLoaded.actionPerformed(
                            new PortsLoadedEvent(
                                    provider, 
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
