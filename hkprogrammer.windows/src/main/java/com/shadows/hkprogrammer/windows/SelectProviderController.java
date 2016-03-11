package com.shadows.hkprogrammer.windows;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.shadows.hkprogrammer.windows.controls.lists.ConnectionTypesList;
import com.shadows.hkprogrammer.windows.listners.ProviderTypeChangeListner;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author John
 */
public class SelectProviderController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button syncButton;
    @FXML
    private ChoiceBox connectionTypeDropDown;
    @FXML
    private ChoiceBox comPortDropDown;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectionTypeDropDown.setItems(
                ConnectionTypesList.init()
        );
        connectionTypeDropDown.getSelectionModel().selectedIndexProperty().addListener(
                new ProviderTypeChangeListner(this)
        );
    }    
    
    @FXML
    private void cancelButtonClick(Event e){
        Platform.exit();
    }
    
    public void onConnectionTypeChange(Object selectedValue){
        int Selected = (Integer) selectedValue;
    }
}
