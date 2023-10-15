package com.example.almacenhierritos;

import clases.EmpresaProveedora;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;

public class NuevaOrdenController {
    static NuevaOrdenController controller;
    static Scene scene;
    @FXML
    ComboBox comboboxFormaPago;

    @FXML
    protected void clickBotonBuscar() {

    }
    @FXML
    protected void clickBotonAceptar() {

    }
    @FXML
    protected void clickBotonCancelar() {
        Main.mainStage.setScene(MenuController.scene);
    }

    protected void formasDePago(){
        try {
            ObservableList<String> items = FXCollections.observableArrayList();
            for (EmpresaProveedora.FormaDePago forma :
                    EmpresaProveedora.FormaDePago.values()) {
                items.add(forma.toString());
            }
            comboboxFormaPago.setItems(items);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void desplegableProductos(){
        try {
            ObservableList<String> items = FXCollections.observableArrayList();
            //TODO Acá se debe bajar la lista de productos de esta empresa

            //TODO Acá se agregan los nombres de los productos que ofrece la empresa a la lista desplegable
            /*for(Producto producto : listaProductosDeLaEmpresa){

            }*/
            //Productos temporales
            items.add("Producto 1");
            items.add("Producto 2");
            items.add("Producto 3");

            comboboxFormaPago.setItems(items);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}