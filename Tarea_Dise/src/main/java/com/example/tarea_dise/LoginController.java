package com.example.tarea_dise;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private ComboBox<String> cmbRol;

    @FXML
    public void initialize() {
        cmbRol.getItems().addAll(
                "--Seleccionar --", "Administrador", "Vendedor");

    }

    @FXML
    private void ingresar(ActionEvent event) throws IOException {

        String usuario = txtUsuario.getText().trim();
        String password = txtPassword.getText().trim();
        String rol = cmbRol.getValue();

        if (usuario.isEmpty()) {
            mostrarError("Ingrese el usuario.");
            return;
        }

        if (password.isEmpty()) {
            mostrarError("Ingrese la contraseña.");
            return;
        }

        if (rol == null || rol.equals("--Seleccionar --")) {
            mostrarError("Seleccione un rol.");
            return;
        }

        // Administrador
        if (usuario.equals("admin")
                && password.equals("1234")
                && rol.equals("Administrador")) {

            abrirVentana("dashboard.fxml",
                    "Panel Administrador");


            return;
        }

        // Vendedor
        if (usuario.equals("vendedor")
                && password.equals("1234")
                && rol.equals("Vendedor")) {



            abrirVentana("dashboard.fxml",
                    "Panel Vendedor");

            return;
        }

        mostrarError("Credenciales incorrectas.");
        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource("dashboard.fxml"));

        Scene scene =
                new Scene(loader.load());

        Stage stage =
                new Stage();

        stage.setScene(scene);
        stage.setTitle("MiTienda");
        stage.show();

        Stage loginStage =
                (Stage) txtUsuario.getScene().getWindow();

        loginStage.close();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acceso Correcto");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void abrirVentana(String archivoFXML, String titulo) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(archivoFXML));

            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();

            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();

            Stage loginStage =
                    (Stage) txtUsuario.getScene().getWindow();

            loginStage.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}