/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.managers;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.windows.controls.events.ParametersLoadedEvent;
import com.shadows.hkprogrammer.windows.core.tasks.ParameterSyncTask;
import com.shadows.hkprogrammer.windows.core.tasks.SetParametersTask;
import com.shadows.hkprogrammer.windows.core.tasks.SyncTask;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author John
 */
public class CommunicatorWorkerManager {
    private SyncTask syncWorker;
    private ParameterSyncTask parameterSyncWorker;
    private SetParametersTask parameterSetWorker;
    public ActionListener onSyncStarted;
    public ActionListener onSyncStopped;
    public ActionListener onSyncFailed;
    public ActionListener onSyncCycle;
    public ActionListener onParameterSyncSuccess;
    public ActionListener onParameterSyncFailed;
    public ActionListener onParameterSetSuccess;
    public ActionListener onParameterSetFailed;
    private final Communicator communicator;
    public CommunicatorWorkerManager(Communicator communicator) {
        this.communicator = communicator;
    }
    
    public void StartSyncTask(){
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
        syncWorker.isSyncing = true;    
        syncWorker.onSyncCycleSuccess = onSyncCycle;
        syncWorker.SyncInterval = 10;
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
        
        new Thread(parameterSyncWorker).start();    
    }
    
    public void RunParameterSetTask(ParameterMessage message){                 
        this.parameterSetWorker = new SetParametersTask(communicator);
        parameterSetWorker.setOnRunning((e) -> {
            try {
                DialogManager.ShowLoadingDialog();
            } catch (IOException ex) {                
            }
        });
        parameterSetWorker.setOnSucceeded((e) -> {
            DialogManager.CloseLoadingDialog();
            boolean result = false;
            try{
                result = parameterSetWorker.get();
            }
            catch(InterruptedException | ExecutionException exp){                     
            }            
            if (!result&&onParameterSetFailed != null)
                onParameterSetFailed.actionPerformed(null);
            else if (result&&onParameterSetSuccess != null)
                onParameterSetSuccess.actionPerformed(null);
        });
        parameterSetWorker.setOnFailed((e) -> {
            DialogManager.CloseLoadingDialog();
            try{
                boolean result = parameterSetWorker.get();
            }
            catch(InterruptedException | ExecutionException exp){ 
                if (onParameterSetFailed != null)
                    onParameterSetFailed.actionPerformed(null);
            }
        });                
        parameterSetWorker.setMessage(message);
        new Thread(parameterSetWorker).start();    
    }
}
