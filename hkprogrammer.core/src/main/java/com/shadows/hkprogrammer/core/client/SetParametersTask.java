/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.client;

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
public class SetParametersTask extends RunnableTask<Boolean>{
    private final Communicator communicator;
    private final ParameterMessage message;
    public SetParametersTask(Communicator provider,ParameterMessage message) {
        this.communicator = provider;       
        this.message = message;
    }     

    @Override
    protected Boolean doRoutine() throws Exception {
        synchronized(communicator){            
            communicator.SetParameters(message);
        }         
        return true;
    }
}
