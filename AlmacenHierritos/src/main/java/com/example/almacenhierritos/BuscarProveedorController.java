package com.example.almacenhierritos;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuscarProveedorController {

    static Scene scene;
    static VBox tarjeta;

    @FXML
    TextField textfieldBuscar;
    @FXML
    protected void clickBotonBuscar() {
        insertarTarjeta();
    }
    @FXML
    protected void clickBotonCrear() {
        Main.mainStage.setScene(MenuController.scene);
    }
    @FXML
    protected void clickRegresar() {
        Main.mainStage.setScene(MenuController.scene);
    }

    public void insertarTarjeta(){
        Main.mainStage.setScene(new Scene(tarjeta));
    }

}