package com.shadows.hkprogrammer.windows.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import com.shadows.hkprogrammer.windows.core.listeners.PortsLoadingSuccessListener;
import com.shadows.hkprogrammer.windows.controls.managers.AlertManager;
import com.shadows.hkprogrammer.windows.controls.lists.ConnectionTypesList;
import com.shadows.hkprogrammer.windows.core.providers.UsbConnectionProvider;
import com.shadows.hkprogrammer.windows.core.listeners.ProviderTypeChangeListner;
import com.shadows.hkprogrammer.windows.controls.managers.ProviderWorkerManager;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author John
 */
public class SelectProviderController implements Initializable {
    private ICommunicationProvider SelectedProvider;    
    @FXML
    private Button cancelButton;
    @FXML
    private Button syncButton;
    @FXML
    private ChoiceBox connectionTypeDropDown;
    @FXML
    private ChoiceBox comPortDropDown;

    public ICommunicationProvider getSelectedProvider() {
        return SelectedProvider;
    }
    
    
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
        System.exit(0);
    }
    @FXML
    private void submitButtonClick(Event e) throws IOException{
        String SelectedPortPosition = (String)comPortDropDown.getValue();
        if (SelectedPortPosition == null||SelectedPortPosition.length() <= 0)
            return;        
        try{
            this.SelectedProvider.OpenConnection(SelectedPortPosition);
            Node  source = (Node)  e.getSource(); 
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        }
        catch(Exception exp){
            AlertManager.AlertError("Could not establish connection with "+SelectedPortPosition);
        }
    }
    
    public void LoadPortsList(ArrayList<String> Ports){
        comPortDropDown.setItems(FXCollections.observableArrayList(Ports));
    }
    
    public void onConnectionTypeChange(Object selectedValue){
        int Selected = (Integer) selectedValue;
        switch(Selected){
            case 0:
                SelectedProvider = new UsbConnectionProvider();                
                break;
            default:
                AlertManager.AlertError("Selected invalid provider type!");
                return;
        }
        ProviderWorkerManager manager = new ProviderWorkerManager(SelectedProvider,this);
        manager.RunGetPortsTask();
    }
}
