package com.shadows.hkprogrammer.windows.controllers;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.core.messages.enums.CraftType;
import com.shadows.hkprogrammer.core.messages.enums.TXModel;
import com.shadows.hkprogrammer.windows.controls.eventlistners.ParamaterSyncSuccessListener;
import com.shadows.hkprogrammer.windows.controls.managers.AlertManager;
import com.shadows.hkprogrammer.windows.controls.managers.CommunicatorWorkerManager;
import com.shadows.hkprogrammer.windows.controls.managers.DialogManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {
    private Communicator communicator;
    private CommunicatorWorkerManager manager;
    @FXML
    private Label label;
    
    @FXML
    private ChoiceBox typeChoiceBox;
    
    @FXML
    private ChoiceBox modeChoiceBox;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        typeChoiceBox.setItems(FXCollections.observableArrayList(CraftType.values()));
        modeChoiceBox.setItems(FXCollections.observableArrayList(TXModel.values()));
    }    
    public void openProviderSelect(){
        try {
            SelectProviderController SelectProvider = DialogManager.ShowSelectProviderDialog();
            ICommunicationProvider provider = SelectProvider.getSelectedProvider();
            if (provider != null && provider.isConnectionOpened())
                communicator = new Communicator(provider);
            else 
                throw new IOException("Provider does not have connection opened!");
            manager = new CommunicatorWorkerManager(communicator);
            manager.onParameterSyncSuccess = new ParamaterSyncSuccessListener(this);
            manager.RunParameterSyncTask();
        } catch (IOException | InterruptedException exp) {
            AlertManager.AlertError("Could not initialize application!");
            Platform.exit();
        }
    }
    public void LoadParameters(ParameterMessage message){
        modeChoiceBox.setValue(message.getTXModelType());
        typeChoiceBox.setValue(message.getCraftTypeNum());
    }
}
