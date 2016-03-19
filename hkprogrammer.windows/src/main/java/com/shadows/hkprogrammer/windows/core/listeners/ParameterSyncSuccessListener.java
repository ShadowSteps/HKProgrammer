/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.listeners;

import com.shadows.hkprogrammer.core.client.events.TaskOnSuccessEvent;
import com.shadows.hkprogrammer.core.client.listeners.ATaskOnSuccessListener;
import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.windows.controllers.FXMLController;
import com.shadows.hkprogrammer.windows.core.tasks.ParameterSyncSuccessUITask;
import javafx.application.Platform;

/**
 *
 * @author John
 */
public class ParameterSyncSuccessListener extends ATaskOnSuccessListener<ParameterMessage> {
    private final FXMLController mainController;

    public ParameterSyncSuccessListener(FXMLController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void onSuccess(TaskOnSuccessEvent<ParameterMessage> result) {
        Platform.runLater(new ParameterSyncSuccessUITask(mainController,result.result));
    }
}
