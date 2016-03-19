/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.listeners;

import com.shadows.hkprogrammer.core.client.events.TaskOnSuccessEvent;
import com.shadows.hkprogrammer.core.client.listeners.ATaskOnSuccessListener;
import com.shadows.hkprogrammer.windows.controls.managers.AlertManager;
import com.shadows.hkprogrammer.windows.core.tasks.HideLoadingDialogTask;
import com.shadows.hkprogrammer.windows.core.tasks.ParameterSetSuccessUITask;
import javafx.application.Platform;

/**
 *
 * @author John
 */
public class ParameterSetSuccessListener extends ATaskOnSuccessListener<Boolean> {  
    @Override
    public void onSuccess(TaskOnSuccessEvent<Boolean> result) {
        Platform.runLater(new ParameterSetSuccessUITask());
    }    
}
