/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.tasks;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.core.utils.ByteArray;
import java.util.ArrayList;
import javafx.concurrent.Task;

/**
 *
 * @author John
 */
public class SetParametersTask extends Task<Boolean>{
    public boolean isSuccessful = false;
    private final Communicator communicator;
    private ParameterMessage message;
    public SetParametersTask(Communicator provider) {
        this.communicator = provider;        
    }     
    public void setMessage(ParameterMessage message){
        this.message = message;
    }
    
    @Override
    protected Boolean call() throws Exception {
        isSuccessful = false;
        synchronized(communicator){            
            communicator.SetParameters(message);
        }         
        return isSuccessful = true;
    }    
}
