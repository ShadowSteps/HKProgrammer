/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.eventlistners;

import com.shadows.hkprogrammer.windows.controllers.FXMLController;
import com.shadows.hkprogrammer.core.client.events.PositionSyncEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author John
 */
public class SyncCycleListener implements ActionListener  {
    private final FXMLController mainController;

    public SyncCycleListener(FXMLController mainController) {
        this.mainController = mainController;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        PositionSyncEvent event = (PositionSyncEvent)e;
        mainController.LoadPotmeterValues(event.message);
    }    
}
