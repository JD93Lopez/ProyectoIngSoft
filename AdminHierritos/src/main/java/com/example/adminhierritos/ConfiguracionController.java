package com.example.adminhierritos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class ConfiguracionController {

    static Scene scene;
    static Stage stage;

    @FXML
    Button botonCerrar;

    @FXML
    CheckBox checkBoxDesCliente;

    @FXML
    CheckBox checkBoxIVA;

    @FXML
    CheckBox checkBoxNombre;

    @FXML
    CheckBox checkBoxNIT;

    @FXML
    CheckBox checkBoxDesc;

    @FXML
    CheckBox checkBoxTelefono;

    public void initialize(){
        botonCerrar.setOnMouseClicked(event -> {
            stage.close();
        });
    }

    public void buttonDescuento( ) {
    }

    public void buttonCorreo( ) {
    }

    public void buttonNombre( ) {
    }

    public void buttonNit( ) {
    }

    public void buttonDireccion() {
    }

    public void buttonTelefono( ) {
    }
}
