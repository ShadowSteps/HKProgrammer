/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.events;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.core.messages.PositionValuesMessage;
import java.awt.event.ActionEvent;

/**
 *
 * @author John
 */
public class PositionSyncEvent extends ActionEvent {
    public PositionValuesMessage message;

    public PositionSyncEvent(Communicator communicator,PositionValuesMessage message) {
        super(communicator, ActionEvent.ACTION_LAST, "");
        this.message = message;
    }

}
