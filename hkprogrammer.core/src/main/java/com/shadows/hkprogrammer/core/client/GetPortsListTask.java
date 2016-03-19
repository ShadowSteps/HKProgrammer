/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.client;

import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import java.util.ArrayList;

/**
 *
 * @author John
 */
public class GetPortsListTask extends RunnableTask<ArrayList<String>>{
    private final ICommunicationProvider provider;

    public GetPortsListTask(ICommunicationProvider provider) {
        this.provider = provider;
    }

    @Override
    protected ArrayList<String> doRoutine() {
        ArrayList<String> Ports;
        synchronized(provider){            
            Ports = provider.GetListOfPorts();
        }  
        return Ports;
    }
}
