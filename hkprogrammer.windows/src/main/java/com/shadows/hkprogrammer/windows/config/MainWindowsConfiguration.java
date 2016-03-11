/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.config;

import com.shadows.hkprogrammer.windows.exceptions.ApplicationConfigurationException;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author John
 */
public class MainWindowsConfiguration {
    public static Scene MainForm;

    public static Scene getMainForm() throws ApplicationConfigurationException {
        if (!(MainForm instanceof Scene)){
            throw new ApplicationConfigurationException("MainForm not loaded into configuration!");
        }
        return MainForm;
    }

    public static void setMainForm(Scene MainForm) {        
        MainWindowsConfiguration.MainForm = MainForm;
    }


    public MainWindowsConfiguration() {
    }

    
    
}
