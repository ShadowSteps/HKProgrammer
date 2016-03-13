/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.events;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 *
 * @author John
 */
public class PortsLoadedEvent extends ActionEvent {
    public final ArrayList<String> ports;
    public PortsLoadedEvent(ICommunicationProvider provider,ArrayList<String> Ports) {
        super(provider, ActionEvent.ACTION_LAST, "");
        this.ports = Ports;
    }    
}
