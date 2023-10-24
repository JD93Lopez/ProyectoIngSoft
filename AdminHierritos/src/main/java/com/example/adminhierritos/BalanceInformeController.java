package com.example.adminhierritos;

import Client.Client;
import clases.Cliente;
import clases.Item;
import clases.Producto;
import clases.ProductoVenta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;

public class BalanceInformeController {

    static Scene scene;

    @FXML
    VBox bigBox;

    @FXML
    TableView<Item> table;

    @FXML
    public void initialize() throws RemoteException {
        // Crear columnas
        TableColumn<Item, Integer> idColumn = new TableColumn<>("id Producto ");
        TableColumn<Item, String> nombreColumn = new TableColumn<>("Nombre Producto");
        TableColumn<Item, Integer> cantidadCompraColumn = new TableColumn<>("Cantidad compras");
        TableColumn<Item, Integer> cantidadVentasColumn = new TableColumn<>("Cantidad Ventas ");

        // Configurar cómo se deben mostrar los datos en las columnas
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProductoProperty().asObject());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProductoProperty());
        cantidadCompraColumn.setCellValueFactory(cellData -> cellData.getValue().cantidadCompraProperty().asObject());
        cantidadVentasColumn.setCellValueFactory(cellData -> cellData.getValue().cantidadVentaProperty().asObject());

        // Agregar columnas a la tabla
        table.getColumns().add(idColumn);
        table.getColumns().add(nombreColumn);
        table.getColumns().add(cantidadCompraColumn);
        table.getColumns().add(cantidadVentasColumn);

        //Obtener los dats del producto desde el server
        LinkedList<ProductoVenta> ventasPorProducto = Client.client.informeVentas();
        LinkedList<ProductoVenta> comprasPorProducto = Client.client.obtenerComprasPorProducto();
        // Crear datos


        ObservableList<Item> data = FXCollections.observableArrayList();
        Iterator it1 = comprasPorProducto.iterator();
        Iterator it2;
        ProductoVenta productoVenta1;
        ProductoVenta productoCompra1;
        ProductoVenta productoTemp=null;

        while (it1.hasNext()){
            productoCompra1  = (ProductoVenta) it1.next();
            System.out.println("C "+productoCompra1.getIdProducto());
            it2 = ventasPorProducto.iterator();
            while (it2.hasNext()){
                productoVenta1 = (ProductoVenta) it2.next();
                System.out.println("v "+productoVenta1.getIdProducto());
                if(productoCompra1.getIdProducto()==productoVenta1.getIdProducto()){
                    productoTemp = productoVenta1;
                    data.add(new Item(productoCompra1.getIdProducto(), productoCompra1.getNombreProducto(), (int) productoCompra1.getTotalVentas(), (int) productoVenta1.getTotalVentas()));
                    break;
                }
            }
            if(productoTemp==null){
                data.add(new Item(productoCompra1.getIdProducto(), productoCompra1.getNombreProducto(), (int) productoCompra1.getTotalVentas(), 0));
            }
            productoTemp = null;
        }

        // Establecer la fuente de datos de la tabla
        table.setItems(data);
    }


}
