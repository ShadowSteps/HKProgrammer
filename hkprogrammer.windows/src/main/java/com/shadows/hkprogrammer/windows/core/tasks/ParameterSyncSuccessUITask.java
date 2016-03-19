/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.tasks;

import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.windows.controllers.FXMLController;
import com.shadows.hkprogrammer.windows.controls.managers.AlertManager;
import com.shadows.hkprogrammer.windows.controls.managers.DialogManager;
import javafx.concurrent.Task;

/**
 *
 * @author John
 */
public class ParameterSyncSuccessUITask extends Task<Boolean>{
    private final FXMLController controller;
    private final ParameterMessage message;
    public ParameterSyncSuccessUITask(FXMLController controller,ParameterMessage message) {
        this.controller = controller;
        this.message = message;
    }
    
    @Override
    protected Boolean call() throws Exception {
        controller.LoadParameters(message);
        DialogManager.CloseLoadingDialog();
        AlertManager.AlertSuccess("Successfully synced parameters!");   
        return true;
    }
    
}
