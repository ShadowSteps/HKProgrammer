/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.listeners;

import com.shadows.hkprogrammer.core.client.events.DaemonCycleCompleteEvent;
import com.shadows.hkprogrammer.core.client.listeners.ADaemonCycleCompleteListener;
import com.shadows.hkprogrammer.core.messages.PositionValuesMessage;
import com.shadows.hkprogrammer.windows.controllers.FXMLController;

/**
 *
 * @author John
 */
public class SyncCycleListener extends ADaemonCycleCompleteListener<PositionValuesMessage>{
    private final FXMLController mainController;

    public SyncCycleListener(FXMLController mainController) {
        this.mainController = mainController;
    }
   
    @Override
    public void onCycle(DaemonCycleCompleteEvent<PositionValuesMessage> result) {
        mainController.LoadPotmeterValues(result.cycle);
    }
}
