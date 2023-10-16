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

    static TextArea areaInfo=null;
    static TextField barraBusqueda=null;
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

    public static void limpiarCampos(){
        BuscarClienteController.clienteActual=null;
        BuscarClienteController.barraBusqueda.setText("");
        BuscarClienteController.areaInfo.setText("");
    }

    public void clickBotonSiguiente() {
        areaInfo = textArea;
        barraBusqueda = textfieldBuscar;
        Main.mainStage.setScene(BuscarCliente2Controller.scene);
    }

    public void clickBotonAgCliente( ) {
        Main.mainStage.setScene(AgregarClienteController.scene);
    }

    public void clickBotonPagarCot() {
        Main.mainStage.setScene(CotizacionController.scene);
    }
}
