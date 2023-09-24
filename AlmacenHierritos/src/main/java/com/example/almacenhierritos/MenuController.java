package com.example.almacenhierritos;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    static Scene scene;
    static Stage stage;
    @FXML
    protected void clickNuevaOrden() {
        stage.setScene(NuevaOrdenController.scene);
    }
    @FXML
    protected void clickBuscarProveedor() {
        stage.setScene(BuscarProveedorController.scene);
    }
    @FXML
    protected void clickAccederInventario() {

    }



/*
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
*/
}