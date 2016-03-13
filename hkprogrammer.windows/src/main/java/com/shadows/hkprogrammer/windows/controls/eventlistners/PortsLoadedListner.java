/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.eventlistners;

import com.shadows.hkprogrammer.windows.controllers.SelectProviderController;
import com.shadows.hkprogrammer.windows.controls.events.PortsLoadedEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author John
 */
public class PortsLoadedListner implements ActionListener{
    private final SelectProviderController controller;

    public PortsLoadedListner(SelectProviderController controller) {
        this.controller = controller;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        PortsLoadedEvent event = (PortsLoadedEvent) e;
        controller.LoadPortsList(event.ports);
    }    
}
