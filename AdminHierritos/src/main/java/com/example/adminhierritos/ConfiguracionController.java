package com.example.adminhierritos;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
            cuadroInfoGuardada();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void buttonCorreo( ) {
        try {
            Client.client.actualizarCorreo(textfieldCorreo.getText());
            cuadroInfoGuardada();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void buttonNombre( ) {
        try {
            Client.client.actualizarNombre(textfieldNombre.getText());
            cuadroInfoGuardada();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void buttonNit( ) {
        try {
            Client.client.actualizarNit(textfieldNit.getText());
            cuadroInfoGuardada();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void buttonDireccion() {
        try {
            Client.client.actualizarDireccion(textfieldDireccion.getText());
            cuadroInfoGuardada();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void buttonTelefono( ) {
        try {
            Client.client.actualizarTelefono(textfieldTelefono.getText());
            cuadroInfoGuardada();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private void cuadroInfoGuardada() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Información guardada");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("\nLa información ha sido registrada con éxito");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);

        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }
}
