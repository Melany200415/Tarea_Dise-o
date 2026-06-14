package com.example.tarea_dise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
public class ProductoController {
    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;

    @FXML
    private ComboBox<String> cmbCategoria;

    @FXML
    private ComboBox<String> cmbEstado;
    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    private TableColumn<Producto, String> colEstado;
    private final ObservableList<Producto> listaProductos =
            FXCollections.observableArrayList();
    @FXML
    public void initialize() {

        cmbCategoria.getItems().addAll(
                "Bebidas",
                "Lácteos",
                "Aseo",
                "Snacks"
        );

        cmbEstado.getItems().addAll(
                "Activo",
                "Inactivo"
        );

        colCodigo.setCellValueFactory(
                new PropertyValueFactory<>("codigo"));

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));

        colCategoria.setCellValueFactory(
                new PropertyValueFactory<>("categoria"));

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio"));

        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock"));

        colEstado.setCellValueFactory(
                new PropertyValueFactory<>("estado"));

        tblProductos.setItems(listaProductos);

        tblProductos.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, producto) -> {

                    if (producto != null) {

                        txtCodigo.setText(producto.getCodigo());
                        txtNombre.setText(producto.getNombre());
                        cmbCategoria.setValue(producto.getCategoria());
                        txtPrecio.setText(
                                String.valueOf(producto.getPrecio()));
                        txtStock.setText(
                                String.valueOf(producto.getStock()));
                        cmbEstado.setValue(producto.getEstado());
                    }
                });
    }
    @FXML
    private void nuevo() {

        limpiar();

        txtCodigo.requestFocus();

        mostrarInfo("Ingrese los datos del nuevo producto.");
    }

    @FXML
    private void guardar() {

        if (!validarCampos()) {
            return;
        }

        Producto producto = new Producto(
                txtCodigo.getText(),
                txtNombre.getText(),
                cmbCategoria.getValue(),
                Double.parseDouble(txtPrecio.getText()),
                Integer.parseInt(txtStock.getText()),
                cmbEstado.getValue()
        );

        listaProductos.add(producto);

        mostrarInfo("Producto guardado correctamente.");
        limpiar();
    }

    @FXML
    private void actualizar() {

        Producto producto =
                tblProductos.getSelectionModel().getSelectedItem();

        if (producto == null) {
            mostrarError("Seleccione un producto.");
            return;
        }

        if (!validarCampos()) {
            return;
        }

        producto.setCodigo(txtCodigo.getText());
        producto.setNombre(txtNombre.getText());
        producto.setCategoria(cmbCategoria.getValue());
        producto.setPrecio(
                Double.parseDouble(txtPrecio.getText()));
        producto.setStock(
                Integer.parseInt(txtStock.getText()));
        producto.setEstado(cmbEstado.getValue());

        tblProductos.refresh();

        mostrarInfo("Producto actualizado.");
        limpiar();
    }

    @FXML
    private void eliminar() {

        Producto producto =
                tblProductos.getSelectionModel().getSelectedItem();

        if (producto == null) {
            mostrarError("Seleccione un producto.");
            return;
        }

        Alert alerta =
                new Alert(Alert.AlertType.CONFIRMATION);

        alerta.setTitle("Confirmar");
        alerta.setHeaderText(null);
        alerta.setContentText(
                "¿Desea eliminar este producto?");

        if (alerta.showAndWait().get() ==
                ButtonType.OK) {

            listaProductos.remove(producto);

            mostrarInfo("Producto eliminado.");

            limpiar();
        }
    }

    @FXML
    private void limpiar() {

        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtStock.clear();

        cmbCategoria.setValue(null);
        cmbEstado.setValue(null);

        tblProductos.getSelectionModel().clearSelection();
    }

    private boolean validarCampos() {

        if (txtCodigo.getText().trim().isEmpty()) {
            mostrarError("Ingrese el código.");
            return false;
        }

        if (txtNombre.getText().trim().isEmpty()) {
            mostrarError("Ingrese el nombre.");
            return false;
        }

        if (cmbCategoria.getValue() == null) {
            mostrarError("Seleccione una categoría.");
            return false;
        }

        if (txtPrecio.getText().trim().isEmpty()) {
            mostrarError("Ingrese el precio.");
            return false;
        }

        if (txtStock.getText().trim().isEmpty()) {
            mostrarError("Ingrese el stock.");
            return false;
        }

        if (cmbEstado.getValue() == null) {
            mostrarError("Seleccione el estado.");
            return false;
        }

        try {
            Double.parseDouble(txtPrecio.getText());
        } catch (NumberFormatException e) {
            mostrarError("Precio inválido.");
            return false;
        }

        try {
            Integer.parseInt(txtStock.getText());
        } catch (NumberFormatException e) {
            mostrarError("Stock inválido.");
            return false;
        }

        return true;
    }

    private void mostrarError(String mensaje) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensaje) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();

    }
}

