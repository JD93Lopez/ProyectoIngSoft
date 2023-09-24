package com.example.almacenhierritos;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NuevaOrdenController {

    static Scene scene;
    static Stage stage;
    @FXML
    protected void clickBotonBuscar() {

    }
    @FXML
    protected void clickBotonAceptar() {

    }
    @FXML
    protected void clickBotonCancelar() {
        stage.setScene(MenuController.scene);

    }

/*
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
*/
}