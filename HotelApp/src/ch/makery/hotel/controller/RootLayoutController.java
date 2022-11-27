package ch.makery.hotel.controller;

import ch.makery.hotel.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 * @author Juan Cebrian
 */
public class RootLayoutController {
    // Reference to the main application
    private Main main;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hotel");
        alert.setContentText("Autor: Juan Cebrián");
        alert.showAndWait();
    }

    public void verJavaDoc(ActionEvent event) {
        main.mostrarJavaDocDialog();
    }

    public void verOcupacion(ActionEvent event) {
    }

    public void verTipoHabitacion(ActionEvent event) {
    }
}
