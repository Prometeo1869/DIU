package ch.makery.examen.controller;

import ch.makery.examen.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RootLayoutController {

    private Main main;
    //Llama al método del Main para la ventana secundaria de la foto y el contador de monedas
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
    //Referencia al Main
    public void setMain(Main main) {
        this.main = main;
    }
}