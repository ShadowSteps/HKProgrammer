/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.managers;

import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import com.shadows.hkprogrammer.windows.core.listeners.PortsLoadingSuccessListener;
import com.shadows.hkprogrammer.core.client.GetPortsListTask;
import com.shadows.hkprogrammer.windows.controllers.SelectProviderController;
import com.shadows.hkprogrammer.windows.core.listeners.PortsLoadingFailedListener;
import com.shadows.hkprogrammer.windows.core.listeners.PortsLoadingStartListener;
import javafx.application.Platform;

/**
 *
 * @author John
 */
public class ProviderWorkerManager {
    private GetPortsListTask getPortsListWorker;
    private final ICommunicationProvider provider;
    public PortsLoadingSuccessListener onPortsLoaded;
    private final SelectProviderController controller;
    public ProviderWorkerManager(ICommunicationProvider provider, SelectProviderController controller) {        
        this.provider = provider;
        this.controller = controller;
    }
    
    public void RunGetPortsTask(){
        getPortsListWorker = new GetPortsListTask(provider);
        getPortsListWorker.onStart = new PortsLoadingStartListener();
        getPortsListWorker.onSuccess = new PortsLoadingSuccessListener(controller);
        getPortsListWorker.onError = new PortsLoadingFailedListener();
        new Thread(getPortsListWorker).start();
    }
    
}
