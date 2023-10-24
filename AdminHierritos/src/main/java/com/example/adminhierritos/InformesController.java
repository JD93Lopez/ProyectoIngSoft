package com.example.adminhierritos;

import Client.Client;
import clases.Producto;
import clases.ProductoVenta;
import clases.Usuario;
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

    public void ClickBotonVolver() {
        Main.mainStage.setScene(MenuController.scene);
    }

    private void cargarFxmlVentas(String archivoFxml) {
        panel.getChildren().clear();  // Limpiar el contenido actual
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(archivoFxml));
        try {
            fxmlLoader.setLocation(Main.class.getResource(archivoFxml));
            informeVBox = fxmlLoader.load();
            panel.getChildren().add(informeVBox);

            VentasInformesController ventasInformesController = fxmlLoader.getController();
            String encabezado = String.format("%-15s %-19s %-15s%n","Id producto ","Nombre ", "Cantidad Vendida");
            ventasInformesController.textArea.appendText(encabezado);

            System.out.println("InformesController.cargarFxmlVentas");
            LinkedList<ProductoVenta> test = Client.client.informeVentas();
            test.getFirst().getNombreProducto();

            for (int i = 0; i < test.size(); i++) {
                ProductoVenta productoVenta = test.get(i);

                String fila = String.format("%-22d %-20s  %20.2f%n", productoVenta.getIdProducto(),productoVenta.getNombreProducto(), productoVenta.getTotalVentas());
                ventasInformesController.textArea.appendText(fila);

                System.out.println(fila);
                System.out.println("InformesController.cargarFxmlVentas");
            }
            ProductoVenta productoMasVendido = test.getFirst();
            String masVendido = "\nProducto más vendido: " + productoMasVendido.getNombreProducto();
            ventasInformesController.textArea.appendText(masVendido);


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

            LinkedList<Vendedor> listVendedorMes = Client.client.informeTopVendedoresMes();

            vendedorMesInformeController.textArea.appendText("Top vendedores del mes: "+"\n" );
            vendedorMesInformeController.textArea.appendText("\nNombres: " + listVendedorMes.getFirst().getNombres() + "\n"
                    + listVendedorMes.getFirst().getTipoDocumento().toString().toLowerCase() + ": " + listVendedorMes.getFirst().getNumDocumento() + "\n" + "Total Ventas realizadas en el mes: " +listVendedorMes.getFirst().getDineroTotalVentasMes()+ "\n");

            for (int i = 1; i <listVendedorMes.size() ; i++) {
                vendedorMesInformeController.textArea.appendText("\nNombres: " + listVendedorMes.get(i).getNombres() + "\n"
                    + listVendedorMes.get(i).getTipoDocumento().toString().toLowerCase() + ": " + listVendedorMes.get(i).getNumDocumento() + "\n" + "Total Ventas realizadas en el mes: " +listVendedorMes.get(i).getDineroTotalVentasMes() + "\n");
            }


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
         //   balanceInformeController.textArea.setText();
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


}
