package ch.makery.hotel.controller;

import ch.makery.hotel.Main;
import ch.makery.hotel.model.Cliente;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class VROverviewController {

    @FXML
    private TextField dniTxt;
    @FXML
    private TextField nombreTxt;
    @FXML
    private TextField apellidosTxt;
    @FXML
    private TextField direccionTxt;
    @FXML
    private TextField localidadTxt;
    @FXML
    private TextField provinciaTxt;

    //Reference to the Main
    private Main main;
    //Reference to the selected Cliente
    private Cliente cliente;

    /**
     * Constructor
     */
    public VROverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    public void mostrarDatosCliente(Cliente cliente) {
        // Fill the labels with info from the cliente object.

            dniTxt.setText(cliente.getDni());
            nombreTxt.setText(cliente.getNombre());
            apellidosTxt.setText(cliente.getApellidos());
            direccionTxt.setText(cliente.getDireccion());
            localidadTxt.setText(cliente.getLocalidad());
            provinciaTxt.setText(cliente.getProvincia());

    }

    public void setCliente(Cliente selectedCliente) {
        this.cliente = selectedCliente;

    }

    public boolean isOkClicked() {
        return true;
    }
}
