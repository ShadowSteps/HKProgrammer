/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.eventlistners;

import com.shadows.hkprogrammer.windows.controllers.FXMLController;
import com.shadows.hkprogrammer.windows.controls.managers.AlertManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author John
 */
public class ParamaterSyncSuccessListener implements ActionListener {
    private final FXMLController mainController;

    public ParamaterSyncSuccessListener(FXMLController mainController) {
        this.mainController = mainController;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        AlertManager.AlertSuccess("Successfully synced parameters!");
    }    
}
