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
import javafx.application.Platform;

/**
 *
 * @author John
 */
public class ParamaterSyncFailListener implements ActionListener {
    private final FXMLController mainController;

    public ParamaterSyncFailListener(FXMLController mainController) {
        this.mainController = mainController;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        AlertManager.AlertError("Failed to sync parameters!");
        Platform.exit();
        System.exit(0);
    }    
}
