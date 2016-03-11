package com.shadows.hkprogrammer.windows;

import com.shadows.hkprogrammer.windows.config.MainWindowsConfiguration;
import com.shadows.hkprogrammer.windows.core.DialogManager;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene Main = DialogManager.ShowMainDialog(stage);        
        MainWindowsConfiguration.MainForm = Main;
        Scene SelectProvider = DialogManager.ShowSelectProviderDialog();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
