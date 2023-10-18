package com.example.adminhierritos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class InformesController {

    static Scene scene;

    VBox informe;

    @FXML
    VBox VboxDisplay;

    @FXML
    Button botonVolver;
    @FXML
    Pane panel;


    public void ClickBotonVentas( ) {
     /*   FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ventas_informes_admin.fxml"));
        try {
            informe = fxmlLoader.load();
            VboxDisplay.getChildren().add(informe);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    */
        cargarFxml("ventas_informes_admin.fxml");
    }

    public void ClickBotonVendedor( ) {
        cargarFxml("vendedor_informes_admin.fxml");
    }

    public void ClickBotonBalance( ) {
        cargarFxml("balance_informes_admin.fxml");
    }

    public void ClickBotonDevoluciones() {
        cargarFxml("devoluciones_informes_admin.fxml");
    }

    private void cargarFxml(String archivoFxml) {
        panel.getChildren().clear();  // Limpiar el contenido actual
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(archivoFxml));
        try {
            fxmlLoader.setLocation(Main.class.getResource(archivoFxml));
            informe = fxmlLoader.load();
            panel.getChildren().add(informe);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ClickBotonVolver() {
        Main.mainStage.setScene(MenuController.scene);
    }
}
