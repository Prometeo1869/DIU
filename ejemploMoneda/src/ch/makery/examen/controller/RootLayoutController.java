package ch.makery.examen.controller;

import ch.makery.examen.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RootLayoutController {

    private Main main;

    public void abrirVentana() {
        main.abrirVentanaNueva();
    }
    @FXML
    private void hechoPor() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Examen DIU");
        alert.setContentText("Autor: Juan Cebrián");
        alert.showAndWait();
    }
    public void setMain(Main main) {
        this.main = main;
    }
}
