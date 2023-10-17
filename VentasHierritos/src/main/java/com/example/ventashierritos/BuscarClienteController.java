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
    static BuscarClienteController controller;

    @FXML
    TextArea textArea;
    @FXML
    TextField textfieldBuscar;
    static Cliente clienteActual=null;

    public void clickBotonBuscar( ) throws RemoteException {
        Cliente cliente;

        cliente  =  Client.client.buscarCliente(textfieldBuscar.getText());

        if(cliente.getId()!=null) {
            clienteActual = cliente;
            textArea.setText("\n    Nombres: " + cliente.getNombres() + "\n    "
                    + cliente.getTipoDocumento().toString().toLowerCase() + ": " + cliente.getNumDocumento() + "\n    "
                    + "\n    Teléfono: " + cliente.getTelefono() + "\n    "
                    + "\n    Dirección: " + cliente.getDireccion() + "\n    "
                    + "Correo Electrónico: " + cliente.getCorreo() + "\n    "
                    + "\n    Tipo de persona: " + cliente.getTipoPersona().toString().toLowerCase() + "\n    "
                    + "Responsable de Iva: " + (cliente.getResponsableDeIva() ? "Sí" : "No") + "\n    "
                    + "\n    \n    \n    "
                    + "Cliente Frecuente: " + (cliente.getClienteFrecuente() ? "Sí" : "No") + "\n    "
                    + "ID interno: " + cliente.getId() + "\n    "
            );
        }else if(textfieldBuscar.getText().equals("")){
            textArea.setText("\n    Rellene la barra de búsqueda con el teléfono o cédula\n    del cliente por favor. ");
        }else{
            textArea.setText("\n    Cliente no encontrado. Por favor agréguelo pulsando el botón\n    \"Agregar Cliente\"");
        }

    }

    public void limpiarCampos(){
        BuscarClienteController.clienteActual=null;
        textfieldBuscar.setText("");
        textArea.setText("");
    }

    public void dibujarCliente() {
        textArea.setText("\n    Nombres: " + clienteActual.getNombres() + "\n    "
                + clienteActual.getTipoDocumento().toString().toLowerCase() + ": " + clienteActual.getNumDocumento() + "\n    "
                + "\n    Teléfono: " + clienteActual.getTelefono() + "\n    "
                + "\n    Dirección: " + clienteActual.getDireccion() + "\n    "
                + "Correo Electrónico: " + clienteActual.getCorreo() + "\n    "
                + "\n    Tipo de persona: " + clienteActual.getTipoPersona().toString().toLowerCase() + "\n    "
                + "Responsable de Iva: " + (clienteActual.getResponsableDeIva() ? "Sí" : "No") + "\n    "
                + "\n    \n    \n    "
                + "Cliente Frecuente: " + (clienteActual.getClienteFrecuente() ? "Sí" : "No") + "\n    "
                + "ID interno: " + clienteActual.getId() + "\n    "
        );
    }

    public void clickBotonSiguiente() {
        Main.mainStage.setScene(BuscarCliente2Controller.scene);
    }

    public void clickBotonAgCliente( ) {
        Main.mainStage.setScene(AgregarClienteController.scene);
    }

    public void clickBotonPagarCot() {
        Main.mainStage.setScene(CotizacionController.scene);
    }
}
