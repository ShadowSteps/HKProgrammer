/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.tasks;

import com.shadows.hkprogrammer.windows.controls.managers.AlertManager;
import com.shadows.hkprogrammer.windows.controls.managers.DialogManager;
import javafx.concurrent.Task;

/**
 *
 * @author John
 */
public class ParameterSetFailUITask extends Task<Boolean>{

    @Override
    protected Boolean call() throws Exception {
        DialogManager.CloseLoadingDialog();
        AlertManager.AlertError("Failed to set parameters!");
        return true;
    }
    
}
