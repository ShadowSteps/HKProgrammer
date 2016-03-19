/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.managers;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.core.client.ParameterSyncTask;
import com.shadows.hkprogrammer.core.client.SetParametersTask;
import com.shadows.hkprogrammer.core.client.SyncDaemon;
import com.shadows.hkprogrammer.windows.controllers.FXMLController;
import com.shadows.hkprogrammer.windows.core.listeners.ParameterSetSuccessListener;
import com.shadows.hkprogrammer.windows.core.listeners.ParameterSyncFailListener;
import com.shadows.hkprogrammer.windows.core.listeners.ParameterSyncSuccessListener;
import com.shadows.hkprogrammer.windows.core.listeners.ParameterSyncStartListener;
import com.shadows.hkprogrammer.windows.core.listeners.SyncCycleListener;
import javafx.application.Platform;

/**
 *
 * @author John
 */
public class CommunicatorWorkerManager {
    private SyncDaemon syncDaemon;
    private ParameterSyncTask parameterSyncWorker;
    private SetParametersTask parameterSetWorker;
    private final Communicator communicator;
    private final FXMLController controller;
    public CommunicatorWorkerManager(Communicator communicator,FXMLController controller) {
        this.communicator = communicator;
        this.controller = controller;
    }
    
    public void StartSyncTask(){
        if(syncDaemon!=null){
            StopSyncTask();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {                
            }
        }
        this.syncDaemon = new SyncDaemon(communicator);  
        syncDaemon.onCycleComplete = new SyncCycleListener(controller);
        new Thread(syncDaemon).start();
    }
    
    public void StopSyncTask(){        
        syncDaemon.stop();
    }
    
    public void RunParameterSyncTask(){   
        this.parameterSyncWorker = new ParameterSyncTask(communicator);   
        parameterSyncWorker.onSuccess = new ParameterSyncSuccessListener(controller);
        parameterSyncWorker.onError = new ParameterSyncFailListener();
        parameterSyncWorker.onStart = new ParameterSyncStartListener();
        new Thread(parameterSyncWorker).start();
    }
    
    public void RunParameterSetTask(ParameterMessage message){                 
        this.parameterSetWorker = new SetParametersTask(communicator,message);    
        parameterSetWorker.onSuccess = new ParameterSetSuccessListener();
        parameterSetWorker.onError = new ParameterSyncFailListener();
        parameterSetWorker.onStart = new ParameterSyncStartListener();
        new Thread(parameterSetWorker).start();  
    }
}
