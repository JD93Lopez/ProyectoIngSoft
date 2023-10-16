package com.example.ventashierritos;

import clases.Cliente;
import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;

public class BuscarClienteController {

    static Scene scene;

    @FXML
    TextArea textArea;
    @FXML
    TextField textfieldBuscar;

    public void clickBotonBuscar( ) throws RemoteException {
        Cliente clientePrueba;

        clientePrueba  =  Client.client.buscarCliente(textfieldBuscar.getText());

        System.out.println(clientePrueba.getTipoPersona());
    }

    public void clickBotonSiguiente() {
        Main.mainStage.setScene(BuscarCliente2Controller.scene);
    }

    public void clickBotonAgCliente( ) {
        Main.mainStage.setScene(AgregarClienteController.scene);
    }

    public void clickBotonPagarCot() {
        Main.mainStage.setScene(FacturaController.scene);
    }
}
