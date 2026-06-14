package com.example.tarea_dise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private AnchorPane contenedorPrincipal;
    private void cargarVista(String archivoFXML) {

        try {

            Parent vista = FXMLLoader.load(
                    getClass().getResource(archivoFXML)
            );

            contenedorPrincipal.getChildren().clear();

            AnchorPane.setTopAnchor(vista, 0.0);
            AnchorPane.setBottomAnchor(vista, 0.0);
            AnchorPane.setLeftAnchor(vista, 0.0);
            AnchorPane.setRightAnchor(vista, 0.0);

            contenedorPrincipal.getChildren().add(vista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirProductos(ActionEvent event) {

        try {

            Parent vista =
                    FXMLLoader.load(
                            getClass().getResource("productos.fxml")
                    );

            contenedorPrincipal.getChildren().clear();

            contenedorPrincipal.getChildren().add(vista);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void cerrarSesion(ActionEvent event) {

        Alert alerta =
                new Alert(Alert.AlertType.CONFIRMATION);

        alerta.setTitle("Cerrar Sesión");
        alerta.setHeaderText(null);
        alerta.setContentText(
                "¿Desea cerrar sesión?");

        if (alerta.showAndWait().get() ==
                ButtonType.OK) {

            try {

                FXMLLoader loader =
                        new FXMLLoader(
                                getClass().getResource(
                                        "Login.fxml"));

                Scene scene =
                        new Scene(loader.load());

                Stage login =
                        new Stage();

                login.setTitle("Mi Tienda");

                login.setScene(scene);

                login.show();

                Stage actual =
                        (Stage) contenedorPrincipal
                                .getScene()
                                .getWindow();

                actual.close();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}