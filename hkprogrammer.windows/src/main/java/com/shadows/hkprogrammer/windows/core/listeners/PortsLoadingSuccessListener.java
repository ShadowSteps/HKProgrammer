/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.listeners;

import com.shadows.hkprogrammer.core.client.events.TaskOnSuccessEvent;
import com.shadows.hkprogrammer.core.client.listeners.ATaskOnSuccessListener;
import com.shadows.hkprogrammer.windows.controllers.SelectProviderController;
import com.shadows.hkprogrammer.windows.core.tasks.HideLoadingDialogTask;
import java.util.ArrayList;
import javafx.application.Platform;

/**
 *
 * @author John
 */
public class PortsLoadingSuccessListener extends ATaskOnSuccessListener<ArrayList<String>>{
    private final SelectProviderController controller;

    public PortsLoadingSuccessListener(SelectProviderController controller) {
        this.controller = controller;
    }
    
    @Override
    public void onSuccess(TaskOnSuccessEvent<ArrayList<String>> result) {        
        this.controller.LoadPortsList(result.result);
        Platform.runLater(new HideLoadingDialogTask());
    }    
}
