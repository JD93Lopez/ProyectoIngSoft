package com.example.almacenhierritos;

import clases.EmpresaProveedora;
import clases.FacturaCompra;
import client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class NuevoProveedorController {
    static NuevoProveedorController controller;
    static Scene scene;
    @FXML
    TextField textfieldBuscar;

    @FXML
    TextField textfieldNombreProveedor;
    @FXML
    TextField textfieldBanco;
    @FXML
    TextField textfieldNIT;
    @FXML
    TextField textfieldCuentaBancaria;
    private static EmpresaProveedora empresaProveedora=null;

    @FXML
    protected void clickBotonBuscar() {
        EmpresaProveedora empresa=null;
        try {
            empresa = Client.client.buscarProveedor(textfieldBuscar.getText());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        if(empresa.getId()!=0){
            empresaProveedora = empresa;
            textfieldNombreProveedor.setText(empresa.getNombre());
            textfieldBanco.setText(empresa.getBanco());
            textfieldNIT.setText(empresa.getNit());
            textfieldCuentaBancaria.setText(empresa.getCuentaBancaria());
        }else{
            empresaProveedora = null;
            cuadroEmpresaNoEncontrada();
        }
    }

    @FXML
    protected void clickBotonAceptar() {
        //Armar y enviar empresa
        EmpresaProveedora empresaAEnviar = new EmpresaProveedora();

        empresaAEnviar.setNombre(textfieldNombreProveedor.getText());
        empresaAEnviar.setBanco(textfieldBanco.getText());
        empresaAEnviar.setNit(textfieldNIT.getText());
        empresaAEnviar.setCuentaBancaria(textfieldCuentaBancaria.getText());
        //TODO textfieldPDescuento
        empresaAEnviar.setpDescuento(0.1);
        if(empresaProveedora!=null){
            empresaAEnviar.setId(empresaProveedora.getId());
        }else {
            empresaAEnviar.setId(0);
        }
        try {
            Client.client.crearEmpresaProveedora(empresaAEnviar);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        //Redibujar tarjetas
        BuscarProveedorController.controller.limpiarGridPane();
        List<EmpresaProveedora> lista;
        try {
            lista = Client.client.ListaEmpresasProveedoras();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        for (EmpresaProveedora empresaProveedora: lista){
            BuscarProveedorController.controller.insertarTarjeta(empresaProveedora);
        }
        //Cambiar pantalla
        Main.mainStage.setScene(BuscarProveedorController.scene);
    }
    @FXML
    protected void clickBotonCancelar() {
        Main.mainStage.setScene(BuscarProveedorController.scene);
    }

    private void cuadroEmpresaNoEncontrada() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Empresa No Encontrada");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("La empresa que está buscando no pudo ser encontrada.");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);

        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }

}