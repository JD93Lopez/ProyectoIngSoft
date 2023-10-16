package com.example.almacenhierritos;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class InventarioController {

    static Scene scene;
    static VBox tarjeta;

    @FXML
    TextField textfieldBuscar;

    @FXML
    ScrollPane scrollPane;

    @FXML
    GridPane gridPane;

    public void clickBotonBuscar() {

    }
    public void clickRegresar() {
        Main.mainStage.setScene(MenuController.scene);
    }
}
