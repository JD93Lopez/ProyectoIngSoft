package com.example.adminhierritos;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuController {

    static Scene scene;


    @FXML
    Button botonConfig;
    @FXML
    protected void clickInformes(){
        Main.mainStage.setScene(InformesController.scene);
    }
    @FXML
    protected void clickActualizarInfo() {
        Main.mainStage.setScene(ActualizarInfoController.scene);
    }

    public void  initialize(){
        botonConfig.setOnMouseClicked(event -> {
            System.out.println("Hola");
            ConfiguracionController.stage = new Stage();
            ConfiguracionController.stage.setScene(ConfiguracionController.scene);
            ConfiguracionController.stage.show();
        });
    }
}
