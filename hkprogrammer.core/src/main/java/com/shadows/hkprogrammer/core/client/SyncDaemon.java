/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.client;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.core.messages.PositionValuesMessage;
import java.io.IOException;


/**
 *
 * @author John
 */
public class SyncDaemon extends RunnableDaemon<PositionValuesMessage>{
    private final Communicator communicator;

    public SyncDaemon(Communicator communicator) {
        this.communicator = communicator;
    }    
    @Override
    protected PositionValuesMessage doCycle() throws IOException {
        synchronized(communicator){            
            communicator.Sync();
        } 
        return communicator.getStatus().Positions;
    }
}
