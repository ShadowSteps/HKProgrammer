/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.listeners;

import com.shadows.hkprogrammer.core.client.events.TaskOnErrorEvent;
import com.shadows.hkprogrammer.core.client.listeners.ATaskOnErrorListener;
import com.shadows.hkprogrammer.windows.controls.managers.AlertManager;
import com.shadows.hkprogrammer.windows.core.tasks.HideLoadingDialogTask;
import javafx.application.Platform;

/**
 *
 * @author John
 */
public class PortsLoadingFailedListener extends ATaskOnErrorListener{
    @Override
    public void onError(TaskOnErrorEvent result) {
        Platform.runLater(new HideLoadingDialogTask());
        AlertManager.AlertError("Could not get ports for provider!");
    }
}
