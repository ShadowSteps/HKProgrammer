/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core;

import com.shadows.hkprogrammer.windows.MainApp;
import com.shadows.hkprogrammer.windows.config.MainWindowsConfiguration;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author John
 */
public class DialogManager {
    public static Scene ShowDialog(Stage stage,String DialogViewName,String Title) throws IOException{
        Parent root = FXMLLoader.load(MainApp.class.getResource(DialogViewName));        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");        
        stage.setTitle(Title);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show(); 
        return scene;
    }
    public static Scene ShowMainDialog(Stage stage) throws IOException{
        return ShowDialog(stage, "/fxml/Scene.fxml", "Shadows HK Programmer");
    }
    public static Scene ShowSelectProviderDialog() throws IOException{
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UNDECORATED);       
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(MainWindowsConfiguration.MainForm.getWindow());      
        return ShowDialog(dialog, "/fxml/SelectProvider.fxml", "Please select connection");                
    }
}
