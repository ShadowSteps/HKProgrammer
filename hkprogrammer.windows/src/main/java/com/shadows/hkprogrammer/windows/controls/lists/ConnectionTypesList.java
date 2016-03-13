/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.lists;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author John
 */
public class ConnectionTypesList {
    public static ObservableList<String> init(){
        return FXCollections.observableArrayList(
                "USB COM port",
                "Bluetooth"
        );
    }
}
