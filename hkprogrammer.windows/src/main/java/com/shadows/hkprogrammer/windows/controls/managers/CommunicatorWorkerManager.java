/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.managers;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.windows.controls.events.ParametersLoadedEvent;
import com.shadows.hkprogrammer.windows.core.tasks.ParameterSyncTask;
import com.shadows.hkprogrammer.windows.core.tasks.SyncTask;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author John
 */
public class CommunicatorWorkerManager {
    private final SyncTask syncWorker;
    private final ParameterSyncTask parameterSyncWorker;
    public ActionListener onSyncStarted;
    public ActionListener onSyncStopped;
    public ActionListener onSyncFailed;
    public ActionListener onParameterSyncSuccess;
    public ActionListener onParameterSyncFailed;
    public CommunicatorWorkerManager(Communicator communicator) {
        this.syncWorker = new SyncTask(communicator);
        syncWorker.setOnSucceeded((e) -> {
            boolean result = false;
            try{
                result = syncWorker.get();
            }
            catch(InterruptedException | ExecutionException exp){                     
            }            
            if (!result&&onSyncFailed != null)
                onSyncFailed.actionPerformed(null);
        });
        syncWorker.setOnFailed((e) -> {
            try{
                boolean result = syncWorker.get();
            }
            catch(InterruptedException | ExecutionException exp){ 
                if (onSyncFailed != null)
                    onSyncFailed.actionPerformed(null);
            }
        });
        this.parameterSyncWorker = new ParameterSyncTask(communicator);
        parameterSyncWorker.setOnRunning((e) -> {
            try {
                DialogManager.ShowLoadingDialog();
            } catch (IOException ex) {                
            }
        });
        parameterSyncWorker.setOnSucceeded((e) -> {
            DialogManager.CloseLoadingDialog();
            boolean result = false;
            try{
                result = parameterSyncWorker.get();
            }
            catch(InterruptedException | ExecutionException exp){                     
            }            
            if (!result&&onParameterSyncFailed != null)
                onParameterSyncFailed.actionPerformed(null);
            else if (result&&onParameterSyncSuccess != null)
                onParameterSyncSuccess.actionPerformed(new ParametersLoadedEvent(communicator,parameterSyncWorker.getParameters()));
        });
        parameterSyncWorker.setOnFailed((e) -> {
            DialogManager.CloseLoadingDialog();
            try{
                boolean result = parameterSyncWorker.get();
            }
            catch(InterruptedException | ExecutionException exp){ 
                if (onParameterSyncFailed != null)
                    onParameterSyncFailed.actionPerformed(null);
            }
        });
    }
    
    public void StartSyncTask(){
        syncWorker.isSyncing = true;           
        new Thread(syncWorker).start();
        if (onSyncStarted != null)
            onSyncStarted.actionPerformed(null);     
    }
    
    public void StopSyncTask(){        
        syncWorker.isSyncing = false;
        if (onSyncStopped != null)
            onSyncStopped.actionPerformed(null);
    }
    
    public void RunParameterSyncTask(){                 
        new Thread(parameterSyncWorker).start();    
    }
}
