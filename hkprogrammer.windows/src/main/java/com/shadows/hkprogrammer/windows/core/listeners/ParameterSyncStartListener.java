/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.listeners;

import com.shadows.hkprogrammer.core.client.events.IActionEvent;
import com.shadows.hkprogrammer.core.client.listeners.IActionListener;
import com.shadows.hkprogrammer.windows.core.tasks.ShowLoadingDialogTask;
import javafx.application.Platform;

/**
 *
 * @author John
 */
public class ParameterSyncStartListener implements IActionListener{

    @Override
    public void trigger(IActionEvent event) {
        Platform.runLater(new ShowLoadingDialogTask());
    }
    
}
