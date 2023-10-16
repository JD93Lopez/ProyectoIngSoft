package com.example.almacenhierritos;

import clases.Producto;
import client.Client;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class InventarioController {

    static Scene scene;
    static VBox tarjeta;

    static InventarioController controller;
    @FXML
    TextField textfieldBuscar;

    @FXML
    ScrollPane scrollPane;

    @FXML
    GridPane gridPane;

    public void clickBotonBuscar() throws RemoteException {

    }
    public void clickRegresar() {
        Main.mainStage.setScene(MenuController.scene);
    }

    protected void ListaProductos () throws RemoteException{
        List<Producto> listPrueba;
        listPrueba = Client.client.ListaProductosInventario();
        for (Producto producto : listPrueba){
            System.out.println(producto.getCodigo());
        }
    }
}
