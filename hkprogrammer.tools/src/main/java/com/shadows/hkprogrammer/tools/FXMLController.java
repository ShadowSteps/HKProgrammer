package com.shadows.hkprogrammer.tools;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button intToByteButton;
       
    @FXML
    private AnchorPane handlerPanel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }          
    
    @FXML
    private void handleIntToByteClick(Event event) throws IOException{                  
        URL url = getClass().getResource("/fxml/IntToByteScene.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 
        handlerPanel.getChildren().clear();///name of pane where you want to put the fxml.
        handlerPanel.getChildren().add(page);
    }
}
