package com.example.ventashierritos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

    public static Stage mainStage;


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("inicio_sesion1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hierritos");
        stage.getIcons().add(new Image(Main.class.getResource("Images/icono.png").openStream()));
        stage.setScene(scene);
        mainStage=stage;
        InicioDeSesionController.scene=scene;
        stage.show();

        fxmlLoader = new FXMLLoader(Main.class.getResource("buscarcliente_ventas.fxml"));
        scene = new Scene(fxmlLoader.load());
        BuscarClienteController.scene = scene;


        fxmlLoader = new FXMLLoader(Main.class.getResource("agregarcliente_ventas.fxml"));
        scene = new Scene(fxmlLoader.load());
        AgregarClienteController.scene = scene;


    }
}