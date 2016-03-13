/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.events;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import java.awt.event.ActionEvent;

/**
 *
 * @author John
 */
public class ParametersLoadedEvent extends ActionEvent {
    public final ParameterMessage parameters;
    public ParametersLoadedEvent(Communicator communicator,ParameterMessage parameters) {
        super(communicator, ActionEvent.ACTION_LAST, "");
        this.parameters = parameters;
    }    
}
