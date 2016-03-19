package com.shadows.hkprogrammer.windows.controllers;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.core.messages.PositionValuesMessage;
import com.shadows.hkprogrammer.core.messages.enums.ControlChannel;
import com.shadows.hkprogrammer.core.messages.enums.CraftType;
import com.shadows.hkprogrammer.core.messages.enums.DRChannel;
import com.shadows.hkprogrammer.core.messages.enums.HeliEndPoint;
import com.shadows.hkprogrammer.core.messages.enums.MixDestination;
import com.shadows.hkprogrammer.core.messages.enums.MixSource;
import com.shadows.hkprogrammer.core.messages.enums.MixSwitch;
import com.shadows.hkprogrammer.core.messages.enums.SwashChannel;
import com.shadows.hkprogrammer.core.messages.enums.TXModel;
import com.shadows.hkprogrammer.core.messages.values.MixSetting;
import com.shadows.hkprogrammer.core.messages.values.ParameterDRValue;
import com.shadows.hkprogrammer.core.messages.values.PitchCurve;
import com.shadows.hkprogrammer.core.messages.values.PotmeterEndPoint;
import com.shadows.hkprogrammer.core.messages.values.ThrottleCurve;
import com.shadows.hkprogrammer.windows.core.listeners.ParameterSyncFailListener;
import com.shadows.hkprogrammer.windows.core.listeners.ParameterSyncSuccessListener;
import com.shadows.hkprogrammer.windows.core.listeners.ParameterSetFailedListener;
import com.shadows.hkprogrammer.windows.core.listeners.ParameterSetSuccessListener;
import com.shadows.hkprogrammer.windows.core.listeners.SyncCycleListener;
import com.shadows.hkprogrammer.windows.controls.managers.AlertManager;
import com.shadows.hkprogrammer.windows.controls.managers.CommunicatorWorkerManager;
import com.shadows.hkprogrammer.windows.controls.managers.DialogManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

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
    private ArrayList<CheckBox> ReverseBitmaskValues;
    
    @FXML
    private ArrayList<TextField> DROnValues;
    
    @FXML
    private ArrayList<TextField> DROffValues;
    
    @FXML
    private ArrayList<TextField> SubtrimValues;
    
    @FXML
    private ArrayList<TextField> EPRightValues;
    
    @FXML
    private ArrayList<TextField> EPLeftValues;
    
    @FXML
    private ArrayList<TextField> SwashValues;
    
    @FXML
    private ArrayList<TextField> MixDownRates;
    
    @FXML
    private ArrayList<TextField> MixUpRates;
    
    @FXML
    private ArrayList<ChoiceBox> MixDestinations;
    
    @FXML
    private ArrayList<ChoiceBox> MixSources;
    
    @FXML
    private ArrayList<ChoiceBox> MixSwitches;
       
    @FXML
    private ArrayList<TextField> PitchNormalValues;
    
    @FXML
    private ArrayList<TextField> PitchIDValues;
    
    @FXML
    private ArrayList<TextField> ThrottleNormalValues;
    
    @FXML
    private ArrayList<TextField> ThrottleIDValues;   
    
    @FXML
    private ArrayList<ProgressBar> LeftBars;  
    
    @FXML
    private ArrayList<ProgressBar> RightBars;  
    
    @FXML
    private void closeAppAction(ActionEvent event) throws InterruptedException{
        Platform.exit();
        manager.StopSyncTask();
        Thread.sleep(1000);
        System.exit(0);       
    }
    
    @FXML
    private void onUploadClick(ActionEvent event){
        manager.RunParameterSetTask(this.ExtractParameters());
    }
    
    @FXML
    private void onSaveClick(ActionEvent event){
        File file = DialogManager.ShowSaveFileDialog();
        if (file == null)
            AlertManager.AlertError("No file selected!");
        try {
            try(FileOutputStream stream = new FileOutputStream(file)){
                try (ObjectOutputStream os = new ObjectOutputStream(stream)){
                    os.writeObject(this.ExtractParameters());
                }
            }    
            AlertManager.AlertSuccess("Parameters saved to file successfully!");
        } catch (Exception e) {
            AlertManager.AlertError("Could not save file!");
        }
    }
    
    @FXML
    private void onLoadClick(ActionEvent event){
        File file = DialogManager.ShowOpenFileDialog();
        if (file == null)
            AlertManager.AlertError("No file selected!");
        try {
            try(FileInputStream stream = new FileInputStream(file)){
                try (ObjectInputStream os = new ObjectInputStream(stream)){
                    ParameterMessage message = (ParameterMessage)os.readObject();
                    this.LoadParameters(message);
                }
            }    
            AlertManager.AlertSuccess("Parameters loaded from file successfully!");
        } catch (IOException | ClassNotFoundException e) {
            AlertManager.AlertError("Could not load file!");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        typeChoiceBox.setItems(FXCollections.observableArrayList(CraftType.values()));
        modeChoiceBox.setItems(FXCollections.observableArrayList(TXModel.values()));
        for(int i = 0; i < 3; i++){
            MixDestinations.get(i).setItems(FXCollections.observableArrayList(MixDestination.values()));
            MixSources.get(i).setItems(FXCollections.observableArrayList(MixSource.values()));
            MixSwitches.get(i).setItems(FXCollections.observableArrayList(MixSwitch.values()));
        }
    }    
    public void openProviderSelect(){
        try {
            SelectProviderController SelectProvider = DialogManager.ShowSelectProviderDialog();
            ICommunicationProvider provider = SelectProvider.getSelectedProvider();
            if (provider != null && provider.isConnectionOpened())
                communicator = new Communicator(provider);
            else 
                throw new IOException("Provider does not have connection opened!");
            manager = new CommunicatorWorkerManager(communicator,this);            
            manager.RunParameterSyncTask();
            //manager.StartSyncTask();            
        } catch (IOException | InterruptedException exp) {
            AlertManager.AlertError("Could not initialize application!");
            Platform.exit();
        }
    }
    
    private void ResetBars(){
        for(int i=0; i<6;i++){
            LeftBars.get(i).setProgress(0);
            RightBars.get(i).setProgress(0);
        }
    }
    
    public void LoadPotmeterValues(PositionValuesMessage message){
        ResetBars();
        for(int i=1; i<=6;i++){
            int ChannelInfo = message.getChannelPositionInfo(i);
            double progress = 0;
            if (ChannelInfo > 1500){
                progress = ChannelInfo - 1500;
                progress /= 500;
                RightBars.get(i-1).setProgress(progress);
            } else if (ChannelInfo<1500){
                progress = 1500 - ChannelInfo;
                progress /= 500;
                LeftBars.get(i-1).setProgress(progress);
            }
        }
    }
    
    public void LoadParameters(ParameterMessage message){
        modeChoiceBox.setValue(message.getTXModelType());
        typeChoiceBox.setValue(message.getCraftTypeNum());
        ParameterDRValue[] drValues = message.getDRValues();
        for(int i = 0; i < drValues.length; i++){
            DROnValues.get(i).setText(Integer.toString(drValues[i].getOnValue()));
            DROffValues.get(i).setText(Integer.toString(drValues[i].getOnValue()));
        }
        Boolean[] reverseBitmask = message.getReverseBitmask();
        for(int i = 0; i < reverseBitmask.length; i++){
            ReverseBitmaskValues.get(i).setSelected(reverseBitmask[i]);
        }
        int[] Subtrim = message.getSubtrim();
        for(int i = 0; i < Subtrim.length; i++){
            SubtrimValues.get(i).setText(Integer.toString(Subtrim[i]));
        }
        PotmeterEndPoint[] EndPoints = message.getEndPoints();
        for(int i = 0; i < EndPoints.length; i++){
            EPLeftValues.get(i).setText(Integer.toString(EndPoints[i].getLeft()));
            EPRightValues.get(i).setText(Integer.toString(EndPoints[i].getRigth()));
        }
        int[] Swash = message.getSwash();
        for(int i = 0; i < Swash.length; i++){
            SwashValues.get(i).setText(Integer.toString(Swash[i]));
        }
        MixSetting[] Mix = message.getMixes();
        for(int i = 0; i < Mix.length; i++){
            MixDestinations.get(i).setValue(Mix[i].getDestination());
            MixSources.get(i).setValue(Mix[i].getSource());
            MixSwitches.get(i).setValue(Mix[i].getSwitch());
            MixDownRates.get(i).setText(Integer.toString(Mix[i].getDownrate()));
            MixUpRates.get(i).setText(Integer.toString(Mix[i].getUprate()));
        }
        PitchCurve[] Pitch = message.getPitchCurves();
        for(int i = 0; i < Pitch.length; i++){
            PitchIDValues.get(i).setText(Integer.toString(Pitch[i].getID()));
            PitchNormalValues.get(i).setText(Integer.toString(Pitch[i].getNormal()));
        }
        ThrottleCurve[] Throttle = message.getThrottleCurves();
        for(int i = 0; i < Throttle.length; i++){
            ThrottleIDValues.get(i).setText(Integer.toString(Throttle[i].getID()));
            ThrottleNormalValues.get(i).setText(Integer.toString(Throttle[i].getNormal()));
        }
    }
    
    public ParameterMessage ExtractParameters(){
        ParameterMessage values = new ParameterMessage();
        values.setCraftTypeNum((CraftType)typeChoiceBox.getValue());
        values.setTXModelType((TXModel)modeChoiceBox.getValue());
        for(int i = 0; i < DROnValues.size(); i++){
            int on = Integer.parseInt(DROnValues.get(i).getText()),
                    off = Integer.parseInt(DROffValues.get(i).getText());
            values.setDRValueForChannel(DRChannel.fromInteger(i), on, off);
        }
        for(int i = 0; i < ReverseBitmaskValues.size(); i++){
            values.setReverseBitmaskForChannel(ControlChannel.fromInteger(i), ReverseBitmaskValues.get(i).isSelected());            
        }
        for(int i = 0; i < SubtrimValues.size(); i++){
            values.setSubtrimValueForChannel(ControlChannel.fromInteger(i),
                    Integer.parseInt(SubtrimValues.get(i).getText()));
        }
        for(int i = 0; i < EPLeftValues.size(); i++){
            int left = Integer.parseInt(EPLeftValues.get(i).getText()),
                    right = Integer.parseInt(EPRightValues.get(i).getText());
            values.setEndPointValueForChannel(ControlChannel.fromInteger(i), left, right);            
        }
        for(int i = 0; i < SwashValues.size(); i++){
            values.setSwashValueForChannel(SwashChannel.fromInteger(i), 
                    Integer.parseInt(SwashValues.get(i).getText()));
        }
        for(int i = 0; i < MixDestinations.size(); i++){
            int up = Integer.parseInt(MixDownRates.get(i).getText()),
                    down = Integer.parseInt(MixUpRates.get(i).getText());
            MixSource Source = (MixSource)MixSources.get(i).getValue();
            MixDestination Dest = (MixDestination)MixDestinations.get(i).getValue();
            MixSwitch Switch = (MixSwitch)MixSwitches.get(i).getValue();
            values.setMixSettingsValue(i+1, Dest, Source, Switch, down, up);
        }
        for(int i = 0; i < PitchIDValues.size(); i++){
            int id = Integer.parseInt(PitchIDValues.get(i).getText()),
                    normal = Integer.parseInt(PitchNormalValues.get(i).getText());
            values.setPitchCurveValueForChannel(HeliEndPoint.fromInteger(i), (byte)normal, (byte)id);
        }
        for(int i = 0; i < ThrottleIDValues.size(); i++){
             int id = Integer.parseInt(ThrottleIDValues.get(i).getText()),
                    normal = Integer.parseInt(ThrottleNormalValues.get(i).getText());
            values.setThrottleCurveValueForChannel(HeliEndPoint.fromInteger(i), (byte)normal, (byte)id);
        }
        return values;
    }
}
