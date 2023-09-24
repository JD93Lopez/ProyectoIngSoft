package com.example.adminhierritos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("inicio_sesion1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hierritos");
        stage.getIcons().add(new Image(Main.class.getResource("Images/icono.png").openStream()));
        stage.setScene(scene);
        InicioDeSesionController.stage=stage;
        InicioDeSesionController.scene=scene;
        stage.show();
        fxmlLoader = new FXMLLoader(Main.class.getResource("menu_admin.fxml"));
        scene = new Scene(fxmlLoader.load());
        MenuController.scene=scene;
        MenuController.stage=stage;
    }


    public static void main(String[] args) {
        launch();
    }
}