/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.controls.managers;

import com.shadows.hkprogrammer.windows.MainApp;
import com.shadows.hkprogrammer.windows.controllers.FXMLController;
import com.shadows.hkprogrammer.windows.controllers.SelectProviderController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author John
 */
public class DialogManager {
    private static Stage LoadingDialog = null;
    public static Scene MainForm;
    public static Scene LastOpenForm;
    
    private static FXMLLoader InitializeLaoder(String DialogViewName)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(DialogViewName));
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        return loader;
    }
    
    private static Stage InitializeDialog(Parent root,String Title,Stage stage)
    {
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");        
        stage.setTitle(Title);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        LastOpenForm = scene;
        return stage;
    }
    
    public static Initializable ShowDialog(Stage stage,String DialogViewName,String Title) throws IOException{
        FXMLLoader loader = InitializeLaoder(DialogViewName);        
        Parent root = loader.load(); 
        stage = InitializeDialog(root, Title, stage);        
        stage.show();         
        return loader.getController();
    }
    public static Initializable ShowAndWaitDialog(Stage stage,String DialogViewName,String Title) throws IOException{
        FXMLLoader loader = InitializeLaoder(DialogViewName);
        Parent root = loader.load();        
        stage = InitializeDialog(root, Title, stage); 
        stage.showAndWait();
        return loader.getController();
    }
    public static void ShowMainDialog(Stage stage) throws IOException{
        FXMLController MainController = (FXMLController)ShowDialog(stage, "/fxml/Scene.fxml", "Shadows HK Programmer");
        MainForm = LastOpenForm;
        MainController.openProviderSelect();
    }
    public static SelectProviderController ShowSelectProviderDialog() throws IOException{
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UNDECORATED);       
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(MainForm.getWindow());      
        return (SelectProviderController)ShowAndWaitDialog(dialog, "/fxml/SelectProvider.fxml", "Please select connection");        
    }
    public static void CloseLoadingDialog(){
        if (LoadingDialog!= null){
            LoadingDialog.close();
            LoadingDialog = null;
        }
            
    }
    public static void ShowLoadingDialog() throws IOException{
        LoadingDialog = new Stage();
        LoadingDialog.initStyle(StageStyle.UNDECORATED);       
        LoadingDialog.initModality(Modality.WINDOW_MODAL);
        LoadingDialog.initOwner(MainForm.getWindow());      
        ShowDialog(LoadingDialog, "/fxml/LoadingDialog.fxml", "Loading....");                
    }
    public static File ShowSaveFileDialog(){
        FileChooser fileSelect = new FileChooser();
        fileSelect.setTitle("Save parameters config");
        File file = fileSelect.showSaveDialog((Stage)MainForm.getWindow());
        return file;
    }
    public static File ShowOpenFileDialog(){
        FileChooser fileSelect = new FileChooser();
        fileSelect.setTitle("Load parameters config");
        File file = fileSelect.showOpenDialog((Stage)MainForm.getWindow());
        return file;
    }
}
