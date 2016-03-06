/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.tools.controllers;

import com.shadows.hkprogrammer.core.utils.ByteConvertHelper;
import com.shadows.hkprogrammer.tools.utils.TextFieldValuesHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author John
 */
public class IntToByteSceneController implements Initializable {

    private final ByteConvertHelper convert = new ByteConvertHelper();
    private final TextFieldValuesHelper valuesHelper = new TextFieldValuesHelper();
    @FXML
    private TextField intToByteInput;
    @FXML
    private TextArea byteToIntInput;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleIntToByteArray(Event event){
        String Int = intToByteInput.getText();
        int value = Integer.parseInt(Int);
        byte[] byteValue = convert.IntegerToByte(value);
        valuesHelper.WriteByteArrayToTextArea(byteToIntInput, byteValue);
    }
    
}
