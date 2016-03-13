/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.managers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author John
 */
public class AlertManager {
    public static void AlertError(String Error){
        Alert messageAlert = new Alert(Alert.AlertType.ERROR,Error,ButtonType.OK);
        messageAlert.showAndWait();
    }
    public static void AlertSuccess(String Message){
        Alert messageAlert = new Alert(Alert.AlertType.CONFIRMATION,Message,ButtonType.OK);
        messageAlert.showAndWait();
    }
}
