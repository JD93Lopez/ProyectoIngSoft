package com.example.almacenhierritos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Builder;
import javafx.util.BuilderFactory;

import java.io.IOException;
import java.util.Locale;

public class BuscarProveedorController {

    static Scene scene;
    static VBox tarjeta;

    @FXML
    GridPane gridPane;
    @FXML
    TextField textfieldBuscar;
    @FXML
    protected void clickBotonBuscar() {
        insertarTarjeta();
    }
    @FXML
    protected void clickBotonCrear() {
        Main.mainStage.setScene(MenuController.scene);
    }
    @FXML
    protected void clickRegresar() {
        Main.mainStage.setScene(MenuController.scene);
    }

    public void insertarTarjeta(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tarjetaProducto.fxml"));
            try {
                tarjeta = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TarjetaController tarjetaController = fxmlLoader.getController();
            tarjetaController.setLabelNombreProducto("NOMBREEE");
            gridPane.getChildren().add(tarjeta);
    }

/*    public void insertarTarjeta(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tarjetaProducto.fxml"));
        TarjetaController tarjetaController = fxmlLoader.getController();
        tarjetaController.setLabelNombreProducto("Nombreee");
        tarjetaController.setLabelDescProducto("Descripcioooooon");
        tarjetaController.setTextFieldCantidad("15");
        tarjeta = tarjetaController.getTarjeta();
        gridPane.getChildren().add(tarjeta);
    }*/

}