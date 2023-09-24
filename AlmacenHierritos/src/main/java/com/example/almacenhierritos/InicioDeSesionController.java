package com.example.almacenhierritos;

import client.Client;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;

public class InicioDeSesionController {

    static Scene scene;
    static Stage stage;
    @FXML
    private VBox vbox;
    @FXML
    private Label labelError;
    @FXML
    private TextField textfieldUsuario;

    @FXML
    private TextField textfieldContrasena;

    @FXML
    protected void clickBotonInicio() {
        try {
            if(!Client.client.isConnected()){
                labelError.setText("Ups! Tenemos problemas de conexión.");
                labelError.setStyle("-fx-background-color: #ff6666;");
            }else if(Client.client.iniciarSesion(textfieldUsuario.getText(),textfieldContrasena.getText())){
                stage.setScene(MenuController.scene);
            }else{
                labelError.setText("Usuario o contraseña incorrectos.");
                labelError.setStyle("-fx-background-color: #ff6666;");
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}