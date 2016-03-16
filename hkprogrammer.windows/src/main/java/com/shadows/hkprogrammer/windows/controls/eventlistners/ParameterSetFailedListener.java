/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.eventlistners;

import com.shadows.hkprogrammer.windows.controls.managers.AlertManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author John
 */
public class ParameterSetFailedListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        AlertManager.AlertError("Failed to set parameters!");
    }
    
}
