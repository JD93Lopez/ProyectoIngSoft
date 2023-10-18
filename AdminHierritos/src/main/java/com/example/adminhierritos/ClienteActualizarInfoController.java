package com.example.adminhierritos;

import Client.Client;
import clases.Cliente;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.rmi.RemoteException;

public class ClienteActualizarInfoController {

    static Scene scene;

    static Cliente clienteActual=null;

    @FXML
    static VBox VBoxgrande;

    @FXML
    TextField textfieldBuscar;

    @FXML
    TextField textfieldNombres;

    @FXML
    TextField textfieldNumTel;

    @FXML
    ComboBox comboBoxTipoDoc;

    @FXML
    TextField textfieldNumDoc;

    @FXML
    TextField textfieldDireccion;

    @FXML
    TextField textfieldCorreo;

    @FXML
    ComboBox comboBoxTipoPers;

    @FXML
    ComboBox comboBoxTipoIVA;


    public void ClickBotonBuscar() throws RemoteException {

        Cliente cliente;

        cliente = Client.client.buscarCliente(textfieldBuscar.getText());

        if(cliente.getId()!=null) {
            clienteActual = cliente;
            llenarEspacios(clienteActual);

        }else if(textfieldBuscar.getText().equals("")){
            cuadroRellenarCampos();

        }else{
            cuadroClienteNoEncontrado();
            clienteActual=null;
        }
    }

    public void llenarEspacios(Cliente clienteActual){
        textfieldNombres.setText(clienteActual.getNombres());
        textfieldNumTel.setText(clienteActual.getTelefono());
        textfieldCorreo.setText(clienteActual.getCorreo());
        textfieldDireccion.setText(clienteActual.getDireccion());
        textfieldNumDoc.setText(clienteActual.getNumDocumento());
    }
    public void clickBotonGuardarCambios() {

    }

    private void cuadroClienteNoEncontrado() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cliente No Encontrado");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("\n Cliente no encontrado. Por favor agréguelo llenando los espacios y pulsando el botón \"Crear\"");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);

        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }
    private void cuadroRellenarCampos() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rellenar Campos");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("\"Rellene la barra de búsqueda con el teléfono o cédula\\n    del cliente por favor");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);

        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }
}
