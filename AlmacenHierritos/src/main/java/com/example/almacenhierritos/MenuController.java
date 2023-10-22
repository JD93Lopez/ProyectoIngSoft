package com.example.almacenhierritos;

import clases.EmpresaProveedora;
import client.Client;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class MenuController {

    static Scene scene;
    @FXML
    protected void clickNuevaOrden() {
        NuevaOrdenController.controller.formasDePago();
        Main.mainStage.setScene(NuevaOrdenController.scene);
    }
    @FXML
    protected void clickBuscarProveedor() {
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
        Main.mainStage.setScene(BuscarProveedorController.scene);
    }
    @FXML
    protected void clickAccederInventario() throws RemoteException {
        InventarioController.controller.limpiarGridPane();
        InventarioController.controller.ListaProductos();
        Main.mainStage.setScene(InventarioController.scene);
    }



/*
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
*/
}