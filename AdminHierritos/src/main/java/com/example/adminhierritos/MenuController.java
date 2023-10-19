package com.example.adminhierritos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

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
        FXMLLoader fxmlLoader;
        try {
            Scene scene;
            fxmlLoader = new FXMLLoader(Main.class.getResource("usuario_actualizarinfo_admin.fxml"));
            scene = new Scene(fxmlLoader.load());
            UsuarioActualizarInfoController.scene = scene;
            UsuarioActualizarInfoController.controller = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(Main.class.getResource("cliente_actualizarinfo_admin.fxml"));
            scene = new Scene(fxmlLoader.load());
            ClienteActualizarInfoController.scene=scene;
            ClienteActualizarInfoController.controller = fxmlLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Main.mainStage.setScene(ActualizarInfoController.scene);
    }

    public void  initialize(){
        botonConfig.setOnMouseClicked(event -> {
            ConfiguracionController.stage = new Stage();
            ConfiguracionController.stage.initStyle(StageStyle.UNDECORATED);
            ConfiguracionController.stage.setScene(ConfiguracionController.scene);
            ConfiguracionController.stage.show();
        });
    }
}
