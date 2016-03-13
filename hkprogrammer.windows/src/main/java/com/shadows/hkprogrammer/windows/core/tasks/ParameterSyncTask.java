/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.tasks;

import com.shadows.hkprogrammer.core.communication.Communicator;
import javafx.concurrent.Task;

/**
 *
 * @author John
 */
public class ParameterSyncTask extends Task<Boolean>{
    public int SyncInterval = 100;
    public boolean isSynced = false;
    private final Communicator communicator;

    public ParameterSyncTask(Communicator communicator) {
        this.communicator = communicator;
    }
    
    @Override
    protected Boolean call() throws Exception {
        isSynced = false;
        synchronized(communicator){            
            communicator.ParametersSync();
        } 
        return isSynced = true;
    }    
}
