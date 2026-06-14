module com.example.tarea_dise {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tarea_dise to javafx.fxml;
    exports com.example.tarea_dise;
}