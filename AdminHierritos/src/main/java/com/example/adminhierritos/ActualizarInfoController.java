package com.example.adminhierritos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ActualizarInfoController {

    static Scene scene;
    static VBox newVbox;

    @FXML
    VBox VboxDisplay;

    public void ClickBotonCliente( ) {
        cargarFxmlCliente("cliente_actualizarinfo_admin.fxml");
    }

    public void ClickBotonUsuario() {
        cargarFxmlUsuario("usuario_actualizarinfo_admin.fxml");
    }

    private  void cargarFxmlUsuario(String archivoFxml) {
        VboxDisplay.getChildren().clear();  // Limpiar el contenido actual
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(archivoFxml));
        try {
            fxmlLoader.load();
            UsuarioActualizarInfoController.controller = fxmlLoader.getController();
            UsuarioActualizarInfoController.controller.desplegables();
            VboxDisplay.getChildren().add(UsuarioActualizarInfoController.controller.vBox);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private  void cargarFxmlCliente(String archivoFxml) {
        VboxDisplay.getChildren().clear();  // Limpiar el contenido actual
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(archivoFxml));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ClienteActualizarInfoController.controller = fxmlLoader.getController();
        ClienteActualizarInfoController.controller.desplegables();

        //   newVbox = fxmlLoader.load();
        VboxDisplay.getChildren().add(ClienteActualizarInfoController.controller.VBoxgrande);
    }

    public void ClickBotonVolver(ActionEvent actionEvent) {
        Main.mainStage.setScene(MenuController.scene);
    }
}
