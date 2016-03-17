/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.client;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import javafx.concurrent.Task;

/**
 *
 * @author John
 */
public class ParameterSyncTask extends Task<Boolean>{
    public boolean isSynced = false;
    private final Communicator communicator;
    private ParameterMessage message;
    public ParameterSyncTask(Communicator communicator) {
        this.communicator = communicator;
    }
    
    public ParameterMessage getParameters(){
        return this.message;
    }
    
    @Override
    protected Boolean call() throws Exception {
        isSynced = false;
        synchronized(communicator){            
            communicator.ParametersSync();
        } 
        this.message = communicator.getStatus().Parameters;
        return isSynced = true;
    }    
}
