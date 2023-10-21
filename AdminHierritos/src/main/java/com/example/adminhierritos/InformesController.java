package com.example.adminhierritos;

import Client.Client;
import clases.ProductoVenta;
import clases.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class InformesController {

    static Scene scene;

    VBox informeVBox;

    @FXML
    VBox VboxDisplay;

    @FXML
    Button botonVolver;
    @FXML
    Pane panel;


    public void ClickBotonVentas( ) {
        cargarFxmlVentas("ventas_informes_admin.fxml");
    }

    public void ClickBotonVendedor( ) {
        cargarFxmlVendedorMes("vendedor_informes_admin.fxml");

    }

    public void ClickBotonBalance( ) {
        cargarFxmlBalance("balance_informes_admin.fxml");
    }

    public void ClickBotonDevoluciones() {
        cargarFxmlDevoluciones("devoluciones_informes_admin.fxml");
    }

    private void cargarFxmlVentas(String archivoFxml) {
        panel.getChildren().clear();  // Limpiar el contenido actual
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(archivoFxml));
        try {

            fxmlLoader.setLocation(Main.class.getResource(archivoFxml));
            informeVBox = fxmlLoader.load();
            panel.getChildren().add(informeVBox);

            VentasInformesController ventasInformesController = fxmlLoader.getController();

            LinkedList<ProductoVenta> test = Client.client.informeVentas("2023-10-21 15:08:27.000000");

            String stringInforme;

            String encabezado = String.format("%-15s %-20s%n", "Id producto", "Cantidad Vendida");
            ventasInformesController.textArea.appendText(encabezado);

            for (int i = 0; i < test.size(); i++) {
                ProductoVenta productoVenta = test.get(i);

                String fila = String.format("%18d %20.2f%n", productoVenta.getIdProducto(), productoVenta.getTotalVentas());
                ventasInformesController.textArea.appendText(fila);

                System.out.println(fila);
                System.out.println("InformesController.cargarFxmlVentas");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void cargarFxmlVendedorMes(String archivoFxml) {
        panel.getChildren().clear();  // Limpiar el contenido actual
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(archivoFxml));
        try {

            fxmlLoader.setLocation(Main.class.getResource(archivoFxml));
            informeVBox = fxmlLoader.load();
            panel.getChildren().add(informeVBox);

            VendedorMesInformeController vendedorMesInformeController  = fxmlLoader.getController();

            Vendedor vendedorMes = Client.client.obtenerVendedorMes();

            vendedorMesInformeController.textArea.setText("\n    Nombres: " + vendedorMes.getNombres() + "\n    "
                    + vendedorMes.getTipoDocumento().toString().toLowerCase() + ": " + vendedorMes.getNumDocumento() + "\n  "
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarFxmlBalance(String archivoFxml) {
        panel.getChildren().clear();  // Limpiar el contenido actual
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(archivoFxml));
        try {

            fxmlLoader.setLocation(Main.class.getResource(archivoFxml));
            informeVBox = fxmlLoader.load();
            panel.getChildren().add(informeVBox);

            BalanceInformeController balanceInformeController = fxmlLoader.getController();

            String test = Client.client.informeBalanceMensual();

            balanceInformeController.textArea.setText(test);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void cargarFxmlDevoluciones(String archivoFxml) {
        panel.getChildren().clear();  // Limpiar el contenido actual
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(archivoFxml));
        try {

            fxmlLoader.setLocation(Main.class.getResource(archivoFxml));
            informeVBox = fxmlLoader.load();
            panel.getChildren().add(informeVBox);

            DevolucionesInformesController devolucionesInformesController = fxmlLoader.getController();

            String test = Client.client.informeDevoluciones();

            devolucionesInformesController.textArea.setText(test);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void ClickBotonVolver() {
        Main.mainStage.setScene(MenuController.scene);
    }

}
