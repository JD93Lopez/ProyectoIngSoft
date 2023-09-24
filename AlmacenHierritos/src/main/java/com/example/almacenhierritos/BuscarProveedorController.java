package com.example.almacenhierritos;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BuscarProveedorController {

    static Scene scene;
    static Stage stage;

    @FXML
    TextField textfieldBuscar;
    @FXML
    protected void clickBotonBuscar() {

    }
    @FXML
    protected void clickBotonCrear() {
        stage.setScene(MenuController.scene);
    }
    @FXML
    protected void clickRegresar() {
        stage.setScene(MenuController.scene);
    }

/*
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
*/
}