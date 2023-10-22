package com.example.almacenhierritos;

import clases.Cliente;
import clases.EmpresaProveedora;
import clases.Producto;
import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;

public class AgregarProductoController {
    public static Scene scene;
    public static AgregarProductoController controller;
    @FXML
    TextField textFieldNomProducto;
    @FXML
    TextField textfieldPrecioVenta;
    @FXML
    TextField textfieldPrecioCompra;
    @FXML
    TextField textfieldPorcIVA;
    @FXML
    TextField textfieldDescProducto;
    @FXML
    TextField textfieldCantMinima;
    @FXML
    TextField textfieldCantMaxima;
    public static EmpresaProveedora empresaActual;

    @FXML
    public void clickBotonCancelar() {
        Main.mainStage.setScene(BuscarProveedorController.scene);
    }
    @FXML
    public void clickBotonAceptar() {
        Producto producto = new Producto();
        producto.setNombre(textFieldNomProducto.getText());
        producto.setPrecioVenta(Double.parseDouble(textfieldPrecioVenta.getText()));
        producto.setPrecioCompra(Double.parseDouble(textfieldPrecioCompra.getText()));
        producto.setDescripcion(textfieldDescProducto.getText());
        String descuento = textfieldPorcIVA.getText();
        if(descuento.charAt(descuento.length()-1)=='%'){
            descuento = descuento.substring(0,descuento.length()-1);
            double doble = Double.parseDouble(descuento);
            doble = doble/100;
            descuento = ""+doble;
        }
        producto.setpIva(Double.parseDouble(descuento));
        producto.setCantidadMinima(Double.parseDouble(textfieldCantMinima.getText()));
        producto.setCantidadMaxima(Double.parseDouble(textfieldCantMaxima.getText()));
        try {
            Client.client.enviarProductoInsertar(producto,empresaActual.getId());
            Main.mainStage.setScene(MenuController.scene);
            limpiarCampos();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public void limpiarCampos(){
        empresaActual = null;
        textFieldNomProducto.setText("");
        textfieldPrecioVenta.setText("");
        textfieldPrecioCompra.setText("");
        textfieldPorcIVA.setText("");
        textfieldDescProducto.setText("");
        textfieldCantMinima.setText("");
        textfieldCantMaxima.setText("");
    }
}
