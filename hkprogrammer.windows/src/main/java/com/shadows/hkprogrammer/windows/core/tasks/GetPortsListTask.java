/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.tasks;

import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import java.util.ArrayList;
import javafx.concurrent.Task;

/**
 *
 * @author John
 */
public class GetPortsListTask extends Task<Boolean>{
    public boolean isSuccessful = false;
    private ArrayList<String> Ports = new ArrayList<>();
    private final ICommunicationProvider provider;

    public GetPortsListTask(ICommunicationProvider provider) {
        this.provider = provider;
    }

    public ArrayList<String> getPorts() {
        return Ports;
    }        
    
    @Override
    protected Boolean call() throws Exception {
        isSuccessful = false;
        synchronized(provider){            
            Ports = provider.GetListOfPorts();
        }         
        return isSuccessful = true;
    }    
}
