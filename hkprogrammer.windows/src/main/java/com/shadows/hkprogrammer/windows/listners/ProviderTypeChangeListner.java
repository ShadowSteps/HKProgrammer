/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.listners;

import com.shadows.hkprogrammer.windows.SelectProviderController;
import java.util.Objects;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author John
 */
public class ProviderTypeChangeListner implements ChangeListener {
    private final SelectProviderController controller;
    public ProviderTypeChangeListner(SelectProviderController control){
        controller = control;
    }
    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if (!Objects.equals(oldValue, newValue))
            controller.onConnectionTypeChange(newValue);
    }    
}
