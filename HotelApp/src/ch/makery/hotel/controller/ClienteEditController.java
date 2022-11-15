package ch.makery.hotel.controller;

import ch.makery.hotel.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Dialog to edit details of a Cliente
 *
 * @author Juan Cebrián
 */
public class ClienteEditController {

    @FXML
    private TextField dniTxtField;
    @FXML
    private TextField nombreTxtField;
    @FXML
    private TextField apellidoTxtField;
    @FXML
    private TextField direccionTxtField;
    @FXML
    private TextField localidadTxtField;
    @FXML
    private TextField provinciaTxtField;

    private Stage dialogStage;
    private Cliente cliente;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Cliente cliente) {
        this.cliente = cliente;

        dniTxtField.setText(cliente.getDni());
        apellidoTxtField.setText(cliente.getApellidos());
        nombreTxtField.setText(cliente.getNombre());
        direccionTxtField.setText(cliente.getDireccion());
        localidadTxtField.setText(cliente.getLocalidad());
        provinciaTxtField.setText(cliente.getProvincia());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks Aceptar.
     */
    @FXML
    private void guardarCambios() {
        if (isInputValid()) {
            cliente.setDni(dniTxtField.getText());
            cliente.setApellidos(apellidoTxtField.getText());
            cliente.setNombre(nombreTxtField.getText());
            cliente.setDireccion(direccionTxtField.getText());
            cliente.setLocalidad(localidadTxtField.getText());
            cliente.setProvincia(provinciaTxtField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }
    /**
     * Called when the user clicks Cancelar.
     */
    @FXML
    private void cancelar() {
        dialogStage.close();
    }
    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        // Si DNI está vacio o tiene más o menos de 9 caracteres
        if (dniTxtField.getText() == null || dniTxtField.getText().length() < 9 || dniTxtField.getText().length() > 9) {
            errorMessage += "¡DNI no válido!\n";
        } else {
            String dni = dniTxtField.getText();
            for (int i = 0; i < dni.length() - 1; i++) {
                if(!Character.isDigit(dni.charAt(i))) {
                    errorMessage = "¡Formato de DNI incorrecto!\n(Formato: 00000000X)";
                }
            }
        }
        if (apellidoTxtField.getText() == null || apellidoTxtField.getText().length() == 0) {
            errorMessage += "¡Apellido no válido!\n";
        }
        if (nombreTxtField.getText() == null || nombreTxtField.getText().length() == 0) {
            errorMessage += "¡Nombre no válido!\n";
        }
        if (direccionTxtField.getText() == null || direccionTxtField.getText().length() == 0) {
            errorMessage += "¡Dirección no válida!\n";
        }
        if (provinciaTxtField.getText() == null || provinciaTxtField.getText().length() == 0) {
            errorMessage += "¡Provincia no válida!\n";
        }
        if (localidadTxtField.getText() == null || localidadTxtField.getText().length() == 0) {
            errorMessage += "¡Localidad no válida!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos no válidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }


}
