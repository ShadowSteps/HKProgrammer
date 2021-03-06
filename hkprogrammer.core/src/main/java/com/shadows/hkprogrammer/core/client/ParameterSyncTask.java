/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.client;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.core.messages.ParameterMessage;

/**
 *
 * @author John
 */
public class ParameterSyncTask extends RunnableTask<ParameterMessage>{    
    private final Communicator communicator;
    
    public ParameterSyncTask(Communicator communicator) {
        this.communicator = communicator;
    }
    
    @Override
    protected ParameterMessage doRoutine() throws Exception{
        ParameterMessage message;
        synchronized(communicator){            
            communicator.ParametersSync();
        } 
        return communicator.getStatus().Parameters;
    }
}
