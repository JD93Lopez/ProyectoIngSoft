package com.example.adminhierritos;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class ConfiguracionController {

    static Scene scene;
    static Stage stage;

    @FXML
    Button botonCerrar;
    @FXML
    TextField textfieldDescuento;
    @FXML
    TextField textfieldCorreo;
    @FXML
    TextField textfieldNombre;
    @FXML
    TextField textfieldNit;
    @FXML
    TextField textfieldDireccion;
    @FXML
    TextField textfieldTelefono;

    public void initialize(){
        botonCerrar.setOnMouseClicked(event -> {
            stage.close();
        });
    }

    public void buttonDescuento( ) {
        try {
            Client.client.actualizarDescuentoFrecuente(textfieldDescuento.getText());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void buttonCorreo( ) {
        try {
            Client.client.actualizarCorreo(textfieldCorreo.getText());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void buttonNombre( ) {
        try {
            Client.client.actualizarNombre(textfieldNombre.getText());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void buttonNit( ) {
        try {
            Client.client.actualizarNit(textfieldNit.getText());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void buttonDireccion() {
        try {
            Client.client.actualizarDireccion(textfieldDireccion.getText());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void buttonTelefono( ) {
        try {
            Client.client.actualizarTelefono(textfieldTelefono.getText());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
