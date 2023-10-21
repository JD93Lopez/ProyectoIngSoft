package com.example.almacenhierritos;

import clases.EmpresaProveedora;
import clases.FacturaCompra;
import clases.Producto;
import client.Client;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class NuevaOrdenController {
    static NuevaOrdenController controller;
    static Scene scene;
    @FXML
    TextField textfieldBuscar;
    @FXML
    ComboBox comboboxFormaPago;
    @FXML
    ComboBox comboboxNombreProducto;

    @FXML
    TextField textfieldEmpresaProveedora;
    @FXML
    TextField textfieldNombreVendedor;
    @FXML
    TextField textfieldCantidadProducto;
    @FXML
    TextField textfieldPrecio;
    @FXML
    TextField textfieldPorcentaje;
    private static EmpresaProveedora empresaProveedora=null;
    private static LinkedList<Producto> listaProductosDeLaEmpresa=null;
    private static Producto productoActual;

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
            textfieldEmpresaProveedora.setText(empresa.getNombre());
            NuevaOrdenController.controller.desplegableProductos();
            NuevaOrdenController.controller.textfieldPorcentaje.setText(empresa.getpDescuento()*100+"%");
        }else {
            empresaProveedora=null;
            cuadroEmpresaNoEncontrada();
        }
    }

    @FXML
    protected void clickBotonAceptar() {
        if(empresaProveedora!=null){
            FacturaCompra facturaCompra = new FacturaCompra();
            try {
                facturaCompra.setEmpresaProveedora(empresaProveedora);
                facturaCompra.setFormaDePago(EmpresaProveedora.FormaDePago.valueOf(comboboxFormaPago.getSelectionModel().getSelectedItem().toString()));
                facturaCompra.setNombreVendedor(textfieldNombreVendedor.getText());
                String textoTotal = textfieldPrecio.getText();
                textoTotal = textoTotal.substring(1,textoTotal.length());
                facturaCompra.setTotal(Double.parseDouble(textoTotal));
                LinkedList<Producto> productos = new LinkedList<>();
                productoActual.setExistencias(Double.parseDouble(textfieldCantidadProducto.getText()));
                productos.add(productoActual);
                facturaCompra.setProductos(productos);
                try {
                    Client.client.enviarFacturaDeCompra(facturaCompra);
                    Main.mainStage.setScene(MenuController.scene);
                    FXMLLoader fxmlLoader;
                    fxmlLoader = new FXMLLoader(Main.class.getResource("nuevaorden_almacen.fxml"));
                    NuevaOrdenController.scene = new Scene(fxmlLoader.load());
                    NuevaOrdenController.controller = fxmlLoader.getController();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }catch (Exception e){
                e.printStackTrace();
                cuadroRellenarCampos();
            }
        }
    }
    @FXML
    protected void clickBotonCancelar() {
        Main.mainStage.setScene(MenuController.scene);
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(Main.class.getResource("nuevaorden_almacen.fxml"));
        try {
            NuevaOrdenController.scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        NuevaOrdenController.controller = fxmlLoader.getController();
    }

    @FXML
    protected void cantidadEscrita(){
        if(productoActual!=null){
            if(!textfieldCantidadProducto.getText().equals("")){
                Double totalPorCantidad = Double.parseDouble(textfieldCantidadProducto.getText())*productoActual.getPrecioCompra();
                Double totalConDescuento = totalPorCantidad - (totalPorCantidad*empresaProveedora.getpDescuento());
                textfieldPrecio.setText("$"+totalConDescuento);
            }
        }
    }
    @FXML
    protected void saleElMouse(){
        if(comboboxNombreProducto.getSelectionModel().getSelectedItem()!=null){
            String seleccionado = comboboxNombreProducto.getSelectionModel().getSelectedItem().toString();
            productoActual = buscarSeleccionado(seleccionado);
            if(!textfieldCantidadProducto.getText().equals("")){
                Double totalPorCantidad = Double.parseDouble(textfieldCantidadProducto.getText())*productoActual.getPrecioCompra();
                Double totalConDescuento = totalPorCantidad - (totalPorCantidad*empresaProveedora.getpDescuento());
                textfieldPrecio.setText("$"+totalConDescuento);
            }
        }else{
            productoActual = null;
        }
    }

    private Producto buscarSeleccionado(String seleccionado) {
        Producto temp = new Producto();
        for (Producto producto : listaProductosDeLaEmpresa) {
            temp = producto;
            if (producto.getNombre().equals(seleccionado)) break;
        }
        return temp;
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
        if(empresaProveedora!=null){
            try {
                ObservableList<String> items = FXCollections.observableArrayList();
                //Acá se bajan los productos de la empresa
                listaProductosDeLaEmpresa = Client.client.productosDeLaEmpresa(empresaProveedora.getId());
                //Acá se agregan los nombres de los productos que ofrece la empresa a la lista desplegable
                for(Producto producto : listaProductosDeLaEmpresa){
                    items.add(producto.getNombre());
                }
                comboboxNombreProducto.setItems(items);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            listaProductosDeLaEmpresa=null;
        }
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

    private void cuadroRellenarCampos() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rellenar Campos");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("Por favor rellene todos los campos.");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);

        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }

}