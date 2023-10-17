package com.example.ventashierritos;

import clases.Producto;
import client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class BuscarCliente2Controller {
    static Scene scene;
    static BuscarCliente2Controller controller;
    @FXML
    TextField textfieldBuscar;
    @FXML
    GridPane gridPane;
    public void clickBotonBuscar() {
    }
    public void clickRegresar() {
        Main.mainStage.setScene(BuscarClienteController.scene);
    }
    public void clickBotonAceptar() {
        Main.mainStage.setScene(FacturaController.scene);
    }
    public void clickBotonCancelar() {
        BuscarClienteController.controller.limpiarCampos();
        Main.mainStage.setScene(BuscarClienteController.scene);
    }
    public void clickBotonHacerCot() {
        Main.mainStage.setScene(CotizacionController.scene);
    }

    static VBox tarjeta;
    static List<VBox> tarjetas = new LinkedList();
    private int col=0;
    private int fil=1;
    public void insertarTarjeta(Producto producto){
        VBox tarjeta;
        //SI HAY MENOS DE 9 Productos debe empezar en la fila 0
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tarjetaProducto.fxml"));
        try {
            tarjeta = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TarjetaController tarjetaController = fxmlLoader.getController();
        tarjetaController.setLabelNombreProducto(producto.getNombre());
        tarjetaController.setLabelDescProducto(producto.getDescripcion());
        tarjetaController.setTextFieldCantidad("0");

        gridPane.add(tarjeta, col++, fil);
        GridPane.setMargin(tarjeta,new Insets(10));
        if (col == 2) {
            col = 0;
            fil++;
        }
        tarjetas.add(tarjeta);
    }

    public void dibujarTarjetasProductos(){
        try {
            List<Producto> listaProductos = Client.client.listaProductosInventario();
            for (Producto producto: listaProductos) {
                insertarTarjeta(producto);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
